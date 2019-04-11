package com.eatfit.presenter.food;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import com.eatfit.R;

public class CustomAdapter extends CursorAdapter{
    private LayoutInflater mInflater;

    public CustomAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View   view    =    mInflater.inflate(R.layout.item, parent, false);
        ViewHolder holder  =   new ViewHolder();
        holder.txtName  = view.findViewById(R.id.txtFoodName);
        holder.textCalories =  view.findViewById(R.id.txtCalories);
        view.setTag(holder);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        //If you want to have zebra lines color effect uncomment below code
        /*if(cursor.getPosition()%2==1) {
             view.setBackgroundResource(R.drawable.item_list_backgroundcolor);
        } else {
            view.setBackgroundResource(R.drawable.item_list_backgroundcolor2);
        }*/

        ViewHolder holder  =   (ViewHolder)    view.getTag();
        holder.txtName.setText(cursor.getString(cursor.getColumnIndex(Food.KEY_name)));
        holder.textCalories.setText(cursor.getString(cursor.getColumnIndex(Food.KEY_calories)));

    }

    static class ViewHolder {
        TextView txtName;
        TextView textCalories;
    }
}
