package com.bluescreen.citizenapp.videollamada;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.bluescreen.citizenapp.R;
import com.bluescreen.citizenapp.videollamada.initsdk.InitAuthSDKCallback;
import com.bluescreen.citizenapp.videollamada.initsdk.InitAuthSDKHelper;

import us.zoom.sdk.JoinMeetingOptions;
import us.zoom.sdk.JoinMeetingParams;
import us.zoom.sdk.MeetingServiceListener;
import us.zoom.sdk.MeetingStatus;
import us.zoom.sdk.ZoomApiError;
import us.zoom.sdk.ZoomError;
import us.zoom.sdk.ZoomSDK;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlumnoVideollamada#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlumnoVideollamada extends Fragment implements InitAuthSDKCallback, MeetingServiceListener, UserLoginCallback.ZoomDemoAuthenticationListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    ZoomSDK mZoomSDK = ZoomSDK.getInstance();
    EditText numberEdit, nameEdit;
    Button b;

    public AlumnoVideollamada() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlumnoVideollamada.
     */
    // TODO: Rename and change types and number of parameters
    public static AlumnoVideollamada newInstance(String param1, String param2) {
        AlumnoVideollamada fragment = new AlumnoVideollamada();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_alumno_videollamada, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        ZoomSDK mZoomSDK = ZoomSDK.getInstance();
        InitAuthSDKHelper.getInstance().initSDK(getContext(), this);
        if (mZoomSDK.isInitialized()) ;
        {


        }
        numberEdit = (EditText) getView().findViewById(R.id.edit_join_number);
        nameEdit = (EditText) getView().findViewById(R.id.edit_join_name);
        b=(Button)getView().findViewById(R.id.bb);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickJoin();
            }
        });
    }

    @Override
    public void onZoomSDKLoginResult(long result) {

    }

    @Override
    public void onZoomSDKLogoutResult(long result) {

    }

    @Override
    public void onZoomIdentityExpired() {

    }

    @Override
    public void onZoomSDKInitializeResult(int i, int i1) {
        if (i != ZoomError.ZOOM_ERROR_SUCCESS) {
            Toast.makeText(getContext(), "Failed to initialize Zoom SDK. Error: " + i + ", internalErrorCode=" + i1, Toast.LENGTH_LONG).show();
        } else {
            ZoomSDK.getInstance().getMeetingSettingsHelper().enable720p(true);
            ZoomSDK.getInstance().getMeetingSettingsHelper().enableShowMyMeetingElapseTime(true);
            ZoomSDK.getInstance().getMeetingSettingsHelper().setVideoOnWhenMyShare(true);
            ZoomSDK.getInstance().getMeetingService().addListener(this);
            Toast.makeText(getContext(), "Sdk iniciado correctamente.", Toast.LENGTH_LONG).show();
            if (mZoomSDK.tryAutoLoginZoom() == ZoomApiError.ZOOM_API_ERROR_SUCCESS) {
                UserLoginCallback.getInstance().addListener(this);
                //showProgressPanel(true);
            } else {
                //showProgressPanel(false);
            }
        }
    }

    @Override
    public void onZoomAuthIdentityExpired() {

    }

    public void onClickJoin() {
        //ZoomSDK.getInstance (). GetMeetingSettingsHelper (). SetAutoConnectVoIPWhenJoinMeeting (true);
        if (!mZoomSDK.isInitialized()) {
            Toast.makeText(getContext(), "Init SDK First", Toast.LENGTH_SHORT).show();
            InitAuthSDKHelper.getInstance().initSDK(getContext(), this);
            return;
        }

        if (ZoomSDK.getInstance().getMeetingSettingsHelper().isCustomizedMeetingUIEnabled()) {
            ZoomSDK.getInstance().getSmsService().enableZoomAuthRealNameMeetingUIShown(false);
        } else {
            ZoomSDK.getInstance().getSmsService().enableZoomAuthRealNameMeetingUIShown(true);
        }
        String number = numberEdit.getText().toString();
        String name = nameEdit.getText().toString();
        JoinMeetingOptions opts = new JoinMeetingOptions();
        opts.no_share = true;
        opts.no_disconnect_audio = true;
        JoinMeetingParams params = new JoinMeetingParams();
        params.meetingNo = number;
        params.displayName = name;

        ZoomSDK.getInstance().getMeetingService().joinMeetingWithParams(getContext(), params);
    }

    @Override
    public void onMeetingStatusChanged(MeetingStatus meetingStatus, int i, int i1) {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        UserLoginCallback.getInstance().removeListener(this);

        if (null != ZoomSDK.getInstance().getMeetingService()) {
            ZoomSDK.getInstance().getMeetingService().removeListener(this);
        }
        InitAuthSDKHelper.getInstance().reset();
    }
}
