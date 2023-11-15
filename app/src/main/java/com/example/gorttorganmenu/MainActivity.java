package com.example.gorttorganmenu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ToolbarWidgetWrapper;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.common.primitives.Ints;

import java.lang.reflect.Method;
import java.util.Arrays;


public class MainActivity extends AppCompatActivity {

    androidx.appcompat.widget.Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (androidx.appcompat.widget.Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("p06menu");
        toolbar.setTitleTextColor(Color.CYAN);
        setSupportActionBar(toolbar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        if(menu.getClass().getSimpleName()
                .equals("MenuBuilder")){
            try{
                Method m = menu.getClass()
                        .getDeclaredMethod (
                                "setOptionalIconsVisible",
                                Boolean.TYPE);
                m.setAccessible(true);
                m.invoke(menu, true);
            }
            catch(NoSuchMethodException e){
                System.err.println("onCreateOptionsMenu");
            }
            catch(Exception e){
                throw new RuntimeException(e);
            }
        }
        return true;
    }

    public void onHelpClick(MenuItem item)
    {
        Toast.makeText(getApplicationContext(),
                "Help clicked", Toast.LENGTH_LONG).show();
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int[] ids = new int[] { // item.getItemId()
                R.id.CART,
                R.id.callPhone,
                R.id.card,
                R.id.video,
                R.id.EMAIL,
                R.id.ADD,
                R.id.COPY,
                R.id.PASTE
        };
        String[] msgs = new String[] {
                "Выбрана корзина",
                "Выбран телефон",
                "Выбрано фото",
                "Выбрано видео",
                "Выбран Email",
                "Выбран пункт \"Добавить\"",
                "Выбран пункт \"Копировать\"",
                "Выбран пункт \"Вставить\"",
        };
        Toast.makeText(getApplicationContext(), msgs[Ints.asList(ids).indexOf(item.getItemId())], Toast.LENGTH_LONG).show();
        return true;
    }
}
