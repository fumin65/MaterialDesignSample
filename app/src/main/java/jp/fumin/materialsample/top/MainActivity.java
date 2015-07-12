package jp.fumin.materialsample.top;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import jp.fumin.materialsample.R;
import jp.fumin.materialsample.card.CardActivity;
import jp.fumin.materialsample.parallax.ParallaxScrollActivity;

public class MainActivity extends ActionBarActivity {

	private static final Menu[] menus = {
			new Menu("Parallax Scroll Sample", ParallaxScrollActivity.class),
			new Menu("Card Sample", CardActivity.class)
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		ListView listView = (ListView) findViewById(R.id.menu_list);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);

		for (Menu menu : menus) {
			adapter.add(menu.mTitle);
		}

		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(MainActivity.this, menus[position].mTarget);
				startActivity(intent);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.getBackground().setAlpha(255);
	}

	private static class Menu {

		private String mTitle;
		private Class<? extends Activity> mTarget;

		private Menu(String title, Class<? extends Activity> target) {
			mTitle = title;
			mTarget = target;
		}
	}
}
