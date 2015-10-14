package com.sample.pulldownmenusample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.List;

/**
 * Created by tanakasatomi on 2015/10/14.
 */
public class MainListAdapter extends ArrayAdapter<VisStoreData> {

  public interface ListListener {
    void clickMenuButton(ImageView view);
  }

  private LayoutInflater inflater;
  private Context context;
  private ListListener listener;

  public MainListAdapter(Context context, int resource, List<VisStoreData> objects, ListListener listener) {
    super(context, resource, objects);
    inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    this.context = context;
    this.listener = listener;
  }

  @Override public View getView(int position, View convertView, ViewGroup parent) {


    ViewHolder viewHolder;

    if (convertView != null) {
      viewHolder = (ViewHolder) convertView.getTag();
    } else {
      convertView = inflater.inflate(R.layout.item_vis_store, parent, false);
      viewHolder = new ViewHolder(convertView);
      convertView.setTag(viewHolder);
    }

    VisStoreData data = getItem(position);

    viewHolder.tableNameTextView.setText(data.getTableName());
    viewHolder.menuImageView.setTag(data.getVisStoreId());

    return convertView;
  }

  class ViewHolder {

    @Bind(R.id.table_name_text_view) TextView tableNameTextView;
    @Bind(R.id.add_button) Button addButton;
    @Bind(R.id.three_dot_menu) ImageView menuImageView;

    @OnClick(R.id.three_dot_menu)
    void onMenuClick(ImageView view) {
      listener.clickMenuButton(view);
    }

    public ViewHolder(View view) {
      ButterKnife.bind(this, view);
    }
  }
}