package org.electricuniverse.lab_43;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Map;

public class MainActivity extends AppCompatActivity
{

    /**
     * Part 5.1: Adding data members to main activity
     * */
    private RecyclerView recycler_view;
    private MovieData md=new MovieData();
    private final MyRecyclerAdapter myRecyclerAdapter = new MyRecyclerAdapter(md.getMoviesList());


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * 2.5: PSet your toolbar as the ActionBar in your Activity’s onCreate method.
         * */
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    /**
     * Part 2.4: FIn your activity implement the onCreateOptionsMenu to inflate the AppBar menus from the xml
     * file you created in 3. For now you can simply create Toast messages to test the SearchView in the
     * onQueryTextSubmit and onQueryTextChange methods. We will implement these later.
     * */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem myActionMenuItem = menu.findItem(R.id.search_action);
        SearchView searchView = (SearchView) myActionMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {

            /**
             * Part 8: Filtering the list. Complete the SearchView.OnQueryTextListener() implementation to invoke the getFilter
             * method on your adapter.
             * */
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                Toast toast = Toast.makeText(getApplicationContext(), "Query Text =" + query, Toast.LENGTH_SHORT);
                toast.show();
                myRecyclerAdapter.getFilter().filter(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                myRecyclerAdapter.getFilter().filter(newText);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Part 5.2: In your onStart initialize the RecyclerView and set its adapter and other adapter attributes. Do not
     * worry about the onListItemClickListener and simply create a toast message in its functions for
     * now.
     * */
    @Override
    protected void onStart()
    {
        super.onStart();
        recycler_view = (RecyclerView) findViewById(R.id.mainRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager( this );
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        layoutManager.scrollToPosition(0);
        recycler_view.setLayoutManager(layoutManager);
        myRecyclerAdapter.setOnItemClickListener(new OnListItemClickListener()
        {
            @Override
            public void onItemClick(View v, int position)
            {
                Map hashMap = myRecyclerAdapter.getItem(position);
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                MovieDetailFragment movieDetailFragment = MovieDetailFragment.newInstance((int)hashMap.get("image"),
                        hashMap.get("name").toString(),hashMap.get("year").toString(),
                        Float.parseFloat(hashMap.get("rating").toString()),hashMap.get("description").toString());
                fragmentTransaction.replace(R.id.detailFragment,movieDetailFragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                fragmentTransaction.commit();

            }

            @Override
            public void onItemLongClick(View v, int position) {

            }
        });
        recycler_view.setAdapter(myRecyclerAdapter);
        recycler_view.setItemAnimator(new DefaultItemAnimator());


    }
}