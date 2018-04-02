# Mobile phone masts
A small project written as a precondition to a job interview.

## Overview

Information about mobile phone masts read in from an asset file in CSV format is stored in a SQLite
database managed by Room, a new Android architecture component.

There are three views on this data and each is kept current by a LiveData observer that invokes list
re-displays when the database is updated.

There are only two activities, one that contains a view pager with three fragments, one for each list,
and an activity for a full-screen modal pop-up that allows a new row to be added to the data set.

## Going through the requirements:

1. Read in the attached file and produce a list sorted by lease amount in ascending order.
Display the first 5 results in a table that can then be toggled between ascending/descending value.

There is a background service (DbHelperIntentService) that initially imports the CSV mast data and inserts
it into the database off the main thread.  When this terminates, the first fragment updates its list view with
data from the top five rows ordered in ascending rental charge.

A text button below the list swaps an ascending query for a descending one and the list updates.

2. Allow the user to enter a new entry to the list of mast data. When adding this entry update the
frontend to display this new entry if it meets the lease amount requirements from point 1.

There is a floating action button that invokes a new activity (AddMastActivity) to present a form into
which data for a new mast can be added.  There's no error checking on this form.

If the form is submitted rather than cancelled, the helper intent service attempts to insert this as a
new row in the database.  If successful, all views update.

3. Display the total rent for all items in this list.

Since the first fragment has an observer callback from a LiveData object, it gets the refreshed data first
before sending it on to its list adapter.  It can snoop on this data to create a sum of rents and display that
beneath the list.

4. Create a dictionary containing tenant name and a count of masts for each tenant. Display a list of
tenants currently on show and the count of masts they each have.  NOTE: Treat "Everything Everywhere Ltd"
and "Hutchinson3G Uk Ltd&Everything Everywhere Ltd" as separate entities.

The "dictionary" is created by a SQL GROUP BY query:

    SELECT tenant_name AS tenantName, COUNT(*) AS num FROM mast
        WHERE tenant_name IN (
            SELECT tenant_name FROM mast
            ORDER BY CAST(current_rent AS DECIMAL)
        LIMIT 5)
        GROUP BY tenant_name
        ORDER BY 2 DESC

It is more efficient to do this that to create a dictionary in Java using HashMap<String, Integer>.
Unfortunately the data hasn't been cleaned, so this result is not especially useful.

5. List the data for rentals with Lease Start Date between 1 June 1999 and 31 Aug 2007.
List these rentals with the date format of: DD/MM/YYYY.

The pane under the third tab shows this list.  We have stored dates as YYYY-MM-DD so that retrieving rows
in a date range is straightforward.

## Testing

There are a couple of Android tests that run and pass just to show the general idea, but time does not
permit high code coverage.
