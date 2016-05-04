package com.diygreen.pageradapterrefresh.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.diygreen.pageradapterrefresh.fragment.FragmentTest;

import java.util.ArrayList;
import java.util.List;

public class FPagerAdapter extends FragmentPagerAdapter {

	private ArrayList<Fragment> fragments;
	private List<Integer> mTypes;
	private FragmentManager fm;
	
	public FPagerAdapter(FragmentManager fm, List<Integer> types) {
		super(fm);
		this.fm = fm;
		this.mTypes = types;
		fragments = new ArrayList<>();
		for (int i = 0, size = types.size(); i < size; i++) {
			fragments.add(FragmentTest.instance(i));
		}
		setFragments(fragments);
	}

	public void updateData(List<Integer> dataList) {
		this.mTypes = dataList;
		ArrayList<Fragment> fragments = new ArrayList<>();
		for (int i = 0, size = dataList.size(); i < size; i++) {
			Log.e("FPagerAdapter", dataList.get(i).toString());
			fragments.add(FragmentTest.instance(dataList.get(i)));
		}
		setFragments(fragments);
	}

	private void setFragments(ArrayList<Fragment> fragments) {
		if(this.fragments != null){
			FragmentTransaction ft = fm.beginTransaction();
			for(Fragment f:this.fragments){
				ft.remove(f);
			}
			ft.commit();
			ft=null;
			fm.executePendingTransactions();
		}
		this.fragments = fragments;
		notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return this.fragments.size();
	}
	
	public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
		return fragments.get(position);
    }
}
