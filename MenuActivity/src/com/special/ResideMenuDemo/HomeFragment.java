package com.special.ResideMenuDemo;

import java.util.ArrayList;

import utils.MyEditText;
import utils.webutils;
import utils.webutils2;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.special.ResideMenu.ResideMenu;

import dmax.dialog.SpotsDialog;

/**
 * User: special
 * Date: 13-12-22
 * Time: 下午1:33
 * Mail: specialcyci@gmail.com
 */

public class HomeFragment extends Fragment {
  
	private Button button;
	private MyEditText editText;
	private TextView yinbiao;
	private TextView ciyi;
	private Button speak;
	private int id;
	private ArrayAdapter<String> adapter;
	private TextView liju;
	private SoundPool aPool;
	private int i;
	private  SharedPreferences mySharedPreferences;
	private Looper looper = Looper.getMainLooper();
	 
	private Handler handler = new Handler() {
		// 接受信息后自动处理，为字段
		@Override
		public void handleMessage(Message msg) {
			
			ArrayList<String> aList = msg.getData().getStringArrayList("list");
			
			ArrayList<String> aList2 = msg.getData()
					.getStringArrayList("list2");
           

         
            SharedPreferences.Editor editor = mySharedPreferences.edit();
           editor.putString(i+"danci", aList.get(4));
           editor.putString(i+"ciyi", aList.get(7));
            editor.putInt("size", i);
           // System.out.println(i+"a");
            i++;
          
           editor.commit();
           
          
			yinbiao.setText("/" + aList.get(5) + "/");

			ciyi.setText(aList.get(7));
			id = aPool.load("http://fy.webxml.com.cn/sound/" + aList.get(8), 1);
			liju.setText("");
			liju.setHint("");
			
			for (int i = 4; i < aList2.size(); i++) {
				String strings[] = aList2.get(i).split("\\|");
				if (strings.length == 2) {
					// System.out.println(strings.length);

					// System.out.println(strings[1]);

					
					liju.setTextSize(18);
					liju.append(strings[0] + "\n");
					liju.append(strings[1] + "\n");
				}

			}

		}

	};

	private View parentView;
    private ResideMenu resideMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.home, container, false);
         mySharedPreferences= getActivity().getSharedPreferences("test", 
  				 Activity.MODE_PRIVATE); 
        i= mySharedPreferences.getInt("size", -1)+1;
       // System.out.println(i);
        setUpViews();
        return parentView;
    }

    private void setUpViews() {
        MenuActivity parentActivity = (MenuActivity) getActivity();
        resideMenu = parentActivity.getResideMenu();

       

        // add gesture operation's ignored views
        FrameLayout ignored_view = (FrameLayout) parentView.findViewById(R.id.ignored_view);
        button = (Button)parentView. findViewById(R.id.button1);
		editText = (MyEditText)parentView. findViewById(R.id.editText1);
		yinbiao = (TextView)parentView. findViewById(R.id.yinbiao);
		ciyi = (TextView) parentView.findViewById(R.id.ciyi);
		speak = (Button)parentView. findViewById(R.id.speak);
		liju = (TextView)parentView. findViewById(R.id.liju);
		aPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
		speak.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO 自动生成的方法存根

				aPool.play(id, 1.0f, 1.0f, 0, 0, 1.0f);

			}
		});

		button.setOnClickListener(new myclick());
		liju.setMovementMethod(ScrollingMovementMethod.getInstance());
		ciyi.setMovementMethod(ScrollingMovementMethod.getInstance());
        resideMenu.addIgnoredView(ignored_view);
    }
    private String aString;
	class myclick implements View.OnClickListener {
		private AlertDialog alertDialog=new SpotsDialog(getActivity(),"等一下");
		
		
         
		@Override
		public void onClick(View v) {
			// TODO 自动生成的方法存根
			 aString = editText.getText().toString()
					.replaceAll(" ", "");
			if(aString.isEmpty()){
				
				Toast.makeText(getContext(), "不能为空",
						0).show();
			}else {
				
				alertDialog.show();
				new Thread(new exec()).start();
			}
			
		}

		public class exec implements Runnable {

			@Override
			public void run() {
				try { 
					
					
				
						
						ArrayList<String> aList = webutils.getaddress(aString);
						ArrayList<String> aList2 = webutils2
								.getaddress(aString);
						
						// ArrayList<String>aList3=webutils3.getaddress(aString);
						Message message = new Message();
						Bundle bundle = message.getData();
						bundle.putStringArrayList("list", aList);
						bundle.putStringArrayList("list2", aList2);
						// bundle.putStringArrayList("list3", aList3);
						message.setData(bundle);
						handler.sendMessage(message);
						alertDialog.dismiss();
					

				} catch (Exception e) {
					// TODO 自动生成的 catch 块
					looper.prepare();
					Toast.makeText(getContext(), "你的网络有问题。。。", 0)
							.show();
					looper.loop();
					e.printStackTrace();
				}

			}

		}

	}


}
