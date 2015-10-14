package com.sample.pulldownmenusample;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tanakasatomi on 2015/10/14.
 */
public class MainFragment extends Fragment implements MainListAdapter.ListListener,
    PullDownLayout.PullDownLayoutListener {

  private PullDownLayout pullDownLayout;

  @Bind(R.id.table_list_view) ListView listView;
  @Bind(R.id.main_layout) RelativeLayout relativeLayout;

  @OnClick(R.id.main_layout)
  void onClickParentView() {
    pullDownLayout.delete();
    Toast.makeText(getActivity(), "relative layout", Toast.LENGTH_SHORT).show();
  }

  @Nullable @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_main, container, false);
    ButterKnife.bind(this, view);

    List<VisStoreData> visStoreDataList = new ArrayList<VisStoreData>();
    VisStoreData visStoreData1 = new VisStoreData();
    visStoreData1.setTableName("aaaaaaa");
    visStoreData1.setVisStoreId("00000001");
    visStoreDataList.add(visStoreData1);
    VisStoreData visStoreData2 = new VisStoreData();
    visStoreData2.setTableName("bbbbbbb");
    visStoreData2.setVisStoreId("00000002");
    visStoreDataList.add(visStoreData2);

    MainListAdapter adapter = new MainListAdapter(getActivity().getApplicationContext(), 0, visStoreDataList, this);

    listView.setAdapter(adapter);

    pullDownLayout = new PullDownLayout(getActivity(), null, this);
    pullDownLayout.setMenu("aaaaaaa1");
    pullDownLayout.setMenu("aaaaaaa2");
    pullDownLayout.setWidth(200);
    pullDownLayout.setHeight(50);
    pullDownLayout.setPosition(0, 30);

    return view;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    ButterKnife.unbind(this);
  }

  @Override public void clickMenuButton(ImageView view) {
    int location[] = new int[2];
    view.getLocationInWindow(location);
    Toast.makeText(getActivity(), "left : " + location[0] + " top : " + location[1] + " " + (String) view.getTag(), Toast.LENGTH_SHORT).show();
    pullDownLayout.setTouchView(view);
    pullDownLayout.show(relativeLayout);
  }

  @Override public void onClickMenu(View view) {
    if (view.getTag() == 0) {
      Toast.makeText(getActivity(), "0", Toast.LENGTH_SHORT).show();
    } else if (view.getTag() == 1) {
      Toast.makeText(getActivity(), "1", Toast.LENGTH_SHORT).show();
    }
  }
}
