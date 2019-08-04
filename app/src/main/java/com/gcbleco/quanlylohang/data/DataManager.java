package com.gcbleco.quanlylohang.data;

import com.gcbleco.quanlylohang.data.db.DbHelper;
import com.gcbleco.quanlylohang.data.network.ApiHelper;
import com.gcbleco.quanlylohang.data.prefs.PreferencesHelper;

/**
 * Created by Gcb Le Co.
 */
public interface DataManager extends DbHelper, PreferencesHelper, ApiHelper {

}
