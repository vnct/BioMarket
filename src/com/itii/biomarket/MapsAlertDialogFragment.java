package com.itii.biomarket;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

// Classe pour générer des alertedialgo
public class MapsAlertDialogFragment extends DialogFragment{
	public static MapsAlertDialogFragment newInstance(int title,String latitute,String longitude) {
		MapsAlertDialogFragment frag = new MapsAlertDialogFragment();
        Bundle args = new Bundle();
        args.putInt("title", title);
        args.putString("latitute", latitute);
        args.putString("longitude", longitude);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int title = getArguments().getInt("title");
        final String latitute = getArguments().getString("latitute");
        final String longitude = getArguments().getString("longitude");

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(R.string.alert_dialog_comment)
                .setPositiveButton(R.string.alert_dialog_ok,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                        	Intent i = new Intent(Intent.ACTION_VIEW, 
			     			Uri.parse("google.navigation:q=" + latitute+","+longitude)); 
			     			startActivity(i);
                        }
                    }
                )
                .setNegativeButton(R.string.alert_dialog_cancel,
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                          
                        }
                    }
                )
                .create();
    }
}
