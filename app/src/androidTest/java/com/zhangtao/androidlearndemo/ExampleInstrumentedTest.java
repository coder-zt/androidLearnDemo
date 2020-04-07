package com.zhangtao.androidlearndemo;

import android.content.Context;
import android.text.TextUtils;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.zhangtao.androidlearndemo", appContext.getPackageName());
//        String table = "tablename";
//        String whereClause = "";
//        String whereArgs = "";
//        String res = "DELETE FROM " + table + (!TextUtils.isEmpty(whereClause) ? " WHERE " + whereClause : "");
//        System.out.println(res);
    }
}
