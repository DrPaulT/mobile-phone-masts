package uk.co.drdv.bink.mobilephonemasts;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import uk.co.drdv.bink.mobilephonemasts.database.Mast;
import uk.co.drdv.bink.mobilephonemasts.database.MastDao;
import uk.co.drdv.bink.mobilephonemasts.database.MastDatabase;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(AndroidJUnit4.class)
public class MastReadWriteTest {

    private MastDao mastDao;
    private MastDatabase mastDatabase;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mastDatabase = Room.inMemoryDatabaseBuilder(context, MastDatabase.class).build();
        mastDao = mastDatabase.mastDao();
    }

    @Test
    public void writeAndReadMast() throws Exception {
        Mast mast = new Mast("Property name", "Address1", "Address2", "Address3", "Address4",
                "Unit name", "Tenant name", "2010-03-01", "2020-07-09", 6, "12000.00");
        mastDao.insert(mast);
        long rows = mastDao.countRows();
        assertThat(rows, equalTo(1L));
        Mast[] masts = getValue(mastDao.selectTop5());
        assertThat(masts.length, equalTo(1));
        assertThat(masts[0].getPropertyName(), equalTo("Property name"));
    }

    @After
    public void closeDb() {
        mastDatabase.close();
    }

    // Force LiveData to evaluate its value.
    public static <T> T getValue(final LiveData<T> liveData) throws InterruptedException {
        final Object[] data = new Object[1];
        final CountDownLatch latch = new CountDownLatch(1);
        Observer<T> observer = new Observer<T>() {
            @Override
            public void onChanged(@Nullable T o) {
                data[0] = o;
                latch.countDown();
                liveData.removeObserver(this);
            }
        };
        liveData.observeForever(observer);
        latch.await(2, TimeUnit.SECONDS);
        //noinspection unchecked
        return (T) data[0];
    }
}
