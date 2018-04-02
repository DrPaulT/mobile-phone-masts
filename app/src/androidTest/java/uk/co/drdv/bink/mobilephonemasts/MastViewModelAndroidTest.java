package uk.co.drdv.bink.mobilephonemasts;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import uk.co.drdv.bink.mobilephonemasts.database.Mast;
import uk.co.drdv.bink.mobilephonemasts.database.MastRepository;
import uk.co.drdv.bink.mobilephonemasts.database.MastViewModel;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MastViewModelAndroidTest {

    @Mock
    private MastRepository mastRepository;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void getMastsAscending() {
        MastViewModel mastViewModel = new MastViewModel();
        Context context = mock(Context.class);
        mastViewModel.initialise(context, mastRepository);
        Mast mast = new Mast("Property name", "Address1", "Address2", "Address3", "Address4",
                "Unit name", "Tenant name", "2010-03-01", "2020-07-09", 6, "12000.00");
        Mast[] masts = {mast};
        MutableLiveData<Mast[]> mutableLiveData = new MutableLiveData<>();
        mutableLiveData.postValue(masts);
        when(mastRepository.getBottom5()).thenReturn(mutableLiveData);
        assertThat(mastViewModel.getMastsAscending(),
                equalTo((LiveData<Mast[]>) mutableLiveData));
    }
}