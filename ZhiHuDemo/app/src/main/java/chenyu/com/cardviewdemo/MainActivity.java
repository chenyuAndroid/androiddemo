package chenyu.com.cardviewdemo;

import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ListView listView;
    private List<MessageObj> mData;
    private Toolbar toolbar;
    private List<String> choices;
    private List<Integer> choiceIcon;
    private MyAdapter recyclerAdapter;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    swipeRefreshLayout.setRefreshing(false);
                    recyclerAdapter.notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initViews();
    }

    private void initViews() {
        /**
         *  初始化Toolbar
         */
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("首页");
        //设置导航图标、添加菜单点击事件要在setSupportActionBar方法之后
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_drawer_home);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_search:
                        Toast.makeText(MainActivity.this, "Search !", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.action_notifications:
                        Toast.makeText(MainActivity.this, "Notification !", Toast.LENGTH_LONG).show();
                        break;
                }
                return true;
            }
        });

        /**
         *  初始化RecyclerView
         */
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new MyAdapter(mData);
        recyclerView.setAdapter(recyclerAdapter);

        /**
         *  初始化swipeRefreshLayout
         */
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
        swipeRefreshLayout.setColorSchemeResources(R.color.color_blue);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Collections.reverse(mData);
                        try {
                            Thread.sleep(1000); //模拟耗时操作
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        mHandler.sendEmptyMessage(1);
                    }
                }).start();
            }
        });

        /**
         *  初始化侧滑菜单 DrawerLayout
         */
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close
        );
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        listView = (ListView) findViewById(R.id.listview);
        ListAdapter adapter = new MenuAdapter(this,choices,choiceIcon);
        listView.setAdapter(adapter);
    }

    private void initData() {
        mData = new ArrayList<MessageObj>();
        MessageObj obj1 = new MessageObj("神盾局",R.mipmap.shield,"5.6K","神盾局是怎么样的一个组织？",
                "神盾局，全称为国土战略防御攻击与后勤保障局，由斯坦·李与杰克·科比联合创造。神盾局是国际安全理事会专门用于处理各种奇异事件的特殊部队");
        mData.add(obj1);
        MessageObj obj2 = new MessageObj("Stark",R.mipmap.stark,"7.8K","钢铁侠是谁？",
                "托尼·斯塔克（小罗伯特·唐尼饰）是“斯塔克工业”的董事长，作为钢铁侠 官方剧照钢铁侠军火商他毁誉不一，但还是过着上流生活。此时，");
        mData.add(obj2);
        MessageObj obj3 = new MessageObj("索尔",R.mipmap.thor,"7.8K","雷神索尔的能力如何？",
                "北欧神话里挥舞着大铁锤、掌控着风暴和闪电的天神，还能用铁锤打开时空之门。暴脾气的他因为自大鲁莽的行为重新点燃了一场古老战争的战火，之后被贬到凡间被迫与人类一起生活。");
        mData.add(obj3);
        MessageObj obj4 = new MessageObj("罗杰斯",R.mipmap.steven,"7.8K","怎么评价美国队长3？",
                "该片根据漫威2006年出版的漫画大事件《内战》改编，背景故事承接于《复仇者联盟2：奥创纪元》事件的余波中，讲述了奥创事件后引发的");
        mData.add(obj4);
        MessageObj obj5 = new MessageObj("黑寡妇",R.mipmap.widow,"7.8K","黑寡妇是一个怎么样的角色？",
                "1928年出生于前苏联的斯大林格勒，自幼被前苏联特工人员训练成特工，身体经前苏联政府基因改造后大大延缓了其衰老速度，并增强其免疫系统以及抗击打能力，加上本身多年的各种体能及精神上的训练");
        mData.add(obj5);
        choices = new ArrayList<String>();
        choiceIcon = new ArrayList<>();
        choices.add("首页");
        choices.add("发现");
        choices.add("关注");
        choices.add("收藏");
        choices.add("圆桌");
        choices.add("私信");
        choiceIcon.add(R.mipmap.ic_main);
        choiceIcon.add(R.mipmap.ic_find);
        choiceIcon.add(R.mipmap.ic_attention);
        choiceIcon.add(R.mipmap.ic_collect);
        choiceIcon.add(R.mipmap.ic_circle);
        choiceIcon.add(R.mipmap.ic_message);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
