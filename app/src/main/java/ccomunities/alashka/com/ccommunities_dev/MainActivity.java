package ccomunities.alashka.com.ccommunities_dev;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.util.Locale;

import ccomunities.alashka.com.ccommunities_dev.Model.Community;
import ccomunities.alashka.com.ccommunities_dev.Fragment.PublicationFragment;
import ccomunities.alashka.com.ccommunities_dev.Fragment.AchievementFragment;
import ccomunities.alashka.com.ccommunities_dev.Adapter.ViewPagerAdapter;


public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.mipmap.icon_tab_publication,
            R.mipmap.icon_tab_achievement
    };

    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setLocale();

        if (isLogged()) {

            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);*/

            fab = (FloatingActionButton) findViewById(R.id.fabMain);

            fab.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), NewPublicationActivity.class);
                    startActivity(intent);

                }
            });

            viewPager = (ViewPager) findViewById(R.id.viewpager);
            setupViewPager(viewPager);
            overrideEvents(fab);

            tabLayout = (TabLayout) findViewById(R.id.tabs);
            tabLayout.setupWithViewPager(viewPager);
            setupTabIcons();

            //_createComunity();

        }
    }

    private void setLocale() {
        Locale.setDefault(new Locale("spa", "ESP"));
    }

    private void _createComunity() {
        Community community = new Community();
        community.setDescription("Comunidad cristiana");
        community.setLocation("Villa Belen");
        community.setName("Comunidad Cristiana Belen");
        community.setResponsibleId("1");
        community.save();
    }

    private void overrideEvents(final FloatingActionButton fab) {
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        fab.show();
                        break;
                    case 1:
                        fab.hide();
                        break;
                    default:
                        fab.hide();
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new PublicationFragment(), getResources().getString(R.string.publication_tab_name));
        adapter.addFragment(new AchievementFragment(), getResources().getString(R.string.achievement_tab_name));

        viewPager.setAdapter(adapter);
    }

    private boolean isLogged() {
        Boolean res = false;
        SharedPreferences sharedPreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        Long username = sharedPreferences.getLong("user_id", -1);
        if (username == -1) {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        } else {
            res = true;
        }
        return res;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.publications_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout_option:
                logout();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        SharedPreferences preferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("user_id");
        editor.commit();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}
