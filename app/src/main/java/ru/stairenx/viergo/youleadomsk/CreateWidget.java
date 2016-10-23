package ru.stairenx.viergo.youleadomsk;

import android.view.MenuItem;

import ru.stairenx.viergo.youleadomsk.activity.AboutActivity;
import ru.stairenx.viergo.youleadomsk.activity.DevActivity;
import ru.stairenx.viergo.youleadomsk.activity.OrgActivity;
import ru.stairenx.viergo.youleadomsk.activity.PartnersActivity;
import ru.stairenx.viergo.youleadomsk.activity.ProfileActivity;
import ru.stairenx.viergo.youleadomsk.activity.ProgramActivity;
import ru.stairenx.viergo.youleadomsk.activity.SpeakersActivity;

/**
 * Created by viergo on 22.09.16.
 */
public class CreateWidget {

public static Class includeNavAction(MenuItem item){
    Class classActivity = null;
    switch (item.getItemId()){
        case R.id.news :
            classActivity = MainActivity.class;
            break;

        case R.id.program :
            classActivity = ProgramActivity.class;
            break;

        case R.id.about :
            classActivity = AboutActivity.class;
            break;

        case R.id.speakers :
            classActivity = SpeakersActivity.class;
            break;

        case R.id.partners :
            classActivity = PartnersActivity.class;
            break;

        case R.id.org :
            classActivity = OrgActivity.class;
            break;

        case R.id.profile :
            classActivity = ProfileActivity.class;
            break;

        case R.id.dev :
            classActivity = DevActivity.class;
            break;
    }
    return classActivity;
}

}
