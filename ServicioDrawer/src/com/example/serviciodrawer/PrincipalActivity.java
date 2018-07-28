package com.example.serviciodrawer;


import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PrincipalActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

	private NavigationDrawerFragment mNavigationDrawerFragment;

	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		
		switch (position) {
		case 1:
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager
			.beginTransaction()
			.replace(R.id.container, MusicaFragment.newInstance(position+1))
			.commit();
			break;
		case 2:
			FragmentManager fragmentManager2 = getFragmentManager();
			fragmentManager2
			.beginTransaction()
			.replace(R.id.container, LocalizacionFragment.newInstance(position+1))
			.commit();
			break;
		default:
			FragmentManager fragmentManager0 = getFragmentManager();
			fragmentManager0
			.beginTransaction()
			.replace(R.id.container,
					PlaceholderFragment.newInstance(position + 1)).commit();
			break;
		}
		
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section1);
			break;
		case 2:
			mTitle = getString(R.string.title_section2);
			break;
		case 3:
			mTitle = getString(R.string.title_section3);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			
			getMenuInflater().inflate(R.menu.principal, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public static class PlaceholderFragment extends Fragment {
		
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_principal,
					container, false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((PrincipalActivity) activity).onSectionAttached(getArguments()
					.getInt(ARG_SECTION_NUMBER));
		}
	}
	
	public static class MusicaFragment extends Fragment{
		
		private static final String ARG_SECTION_NUMBER = "section_number";
		
		public static MusicaFragment newInstance(int sectionNumber){
			MusicaFragment fragment= new MusicaFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}
		
		public MusicaFragment(){
			
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((PrincipalActivity) activity).onSectionAttached(getArguments()
					.getInt(ARG_SECTION_NUMBER));
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.musica_layout,
					container, false);
			return rootView;
		}
		
		
	}
	
	public static class LocalizacionFragment extends Fragment{
		
		private static final String ARG_SECTION_NUMBER = "section_number";
		TextView txV;
		
		public static LocalizacionFragment newInstance(int sectionNumber){
			LocalizacionFragment fragment = new LocalizacionFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}
		
		public LocalizacionFragment(){
			
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.localizacion,
					container, false);
			 txV = (TextView) rootView.findViewById (R.id.txV);
		       try {
				
		        MiServicio servicio= new MiServicio();
		        servicio.setView(rootView.findViewById(R.id.txV));
		       } catch (Exception e) {
					Log.d("Error geolocalizacion", e.toString());
				} 
			
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			
			super.onAttach(activity);
			((PrincipalActivity) activity).onSectionAttached(getArguments()
					.getInt(ARG_SECTION_NUMBER));
		}
		
		
	}

}
