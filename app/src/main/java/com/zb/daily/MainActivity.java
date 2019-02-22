package com.zb.daily;

import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import com.zb.daily.UI.AssetsFragment;
import com.zb.daily.UI.ChartFragment;
import com.zb.daily.UI.IndexFragment;

public class MainActivity extends AppCompatActivity {

    //滑动菜单
    private DrawerLayout drawerLayout;
    //滑动菜单里的菜单项
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);

        //替换fragment
        replaceFragment(new IndexFragment());

        //处理点击菜单项产生的事件
        navigationView = findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_main);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_main:
                        replaceFragment(new IndexFragment());
                        break;
                    case R.id.nav_chart:
                        replaceFragment(new ChartFragment());
                        break;
                    case R.id.nav_assets:
                        replaceFragment(new AssetsFragment());
                        break;
                }
                menuItem.setCheckable(true);//选项可选
                menuItem.setChecked(true);//选项被选中
                drawerLayout.closeDrawers();//关闭滑动窗口
                return true;
            }
        });
    }

    //动态切换fragment
    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }
}
