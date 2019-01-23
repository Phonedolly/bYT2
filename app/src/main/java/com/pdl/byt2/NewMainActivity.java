package com.pdl.byt2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

import com.pdl.byt2.favorite.FavoriteActivity;
import com.pdl.byt2.history.HistoryActivity;
import com.pdl.byt2.utils.TabUtils;

public class NewMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);

        Window window = getWindow();
        window.setNavigationBarColor(000000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_new:
                TabUtils.openInNewTab(this, null, false);
                break;
            case R.id.menu_incognito:
                TabUtils.openInNewTab(this, null, true);
                break;
//            case R.id.menu_reload:
//                mWebView.reload();
//                break;
//            case R.id.menu_add_favorite:
//                setAsFavorite(mWebView.getTitle(), mWebView.getUrl());
//                break;
//            case R.id.menu_share:
//                // Delay a bit to allow popup menu hide animation to play
//                new Handler().postDelayed(() -> shareUrl(mWebView.getUrl()), 300);
//                break;
//            case R.id.menu_search:
//                // Run the search setup
//                showSearch();
//                break;
            case R.id.menu_favorite:
                startActivity(new Intent(this, FavoriteActivity.class));
                break;
            case R.id.menu_history:
                startActivity(new Intent(this, HistoryActivity.class));
                break;
//            case R.id.menu_shortcut:
//                addShortcut();
//                break;
            case R.id.menu_settings:
                startActivity(new Intent(this, SettingsActivity.class));
                break;
//            case R.id.desktop_mode:
//                mWebView.setDesktopMode(!isDesktop);
//                desktopMode.setTitle(getString(isDesktop ?
//                        R.string.menu_desktop_mode : R.string.menu_mobile_mode));
//                desktopMode.setIcon(ContextCompat.getDrawable(this, isDesktop ?
//                        R.drawable.ic_desktop : R.drawable.ic_mobile));
//                break;
//            case R.id.menu_debug:
//                about();
//                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
