package com.example.a777;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = findViewById(R.id.videoView);
    }

    public void onButtonClicked(View v) {
        Toast.makeText(this, "실시간 CCTV로 이동", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.105.113:8081"));
        startActivity(intent);
    }

    public void onButtonClicked2(View v) {
        Toast.makeText(this, "구글 드라이브로 이동", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/"));
        startActivity(intent);
    }

    VideoView videoView;

    public void gallery(View view) {    // 동영상 선택 누르면 실행됨 동영상 고를 갤러리 오픈
        Intent intent = new Intent();
        Toast.makeText(this, "갤러리로 이동", Toast.LENGTH_LONG).show();
        intent.setType("video/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 101);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) { // 갤러리
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                MediaController mc = new MediaController(this); // 비디오 컨트롤 가능하게(일시정지, 재시작 등)
                videoView.setMediaController(mc);

                Uri fileUri = data.getData();
                videoView.setVideoPath(String.valueOf(fileUri));    // 선택한 비디오 경로 비디오뷰에 셋
                videoView.start();  // 비디오뷰 시작
            }
        }
    }

    public void onButtonClicked3(View v) {
        Toast.makeText(this, "전화창으로 이동", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this ,SubActivity.class);
        startActivity(intent);
    }
}

