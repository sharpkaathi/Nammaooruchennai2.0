

package com.ibm.mobileappbuilder.storesreview20160225105920.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import ibmmobileappbuilder.ui.BaseDetailActivity;

/**
 * MonumentsDetailActivity detail activity
 */
public class MonumentsDetailActivity extends BaseDetailActivity {
  
  	@Override
    protected void onCreate(Bundle savedState) {
        super.onCreate(savedState);
        
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected Class<? extends Fragment> getFragmentClass() {
        return MonumentsDetailFragment.class;
    }
}


