package com.example.n2.playergui;

        import android.app.Activity;
        import android.os.Bundle;
        import android.view.Gravity;
        import android.view.View;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.LinearLayout;
        import android.widget.RelativeLayout;
        import android.widget.Spinner;
        import android.widget.TextView;

        import java.util.ArrayList;

public class MainActivity extends Activity implements  View.OnClickListener {

    private RelativeLayout mLayout;
    private EditText usernameInput;
    private LinearLayout column;
    Button addPlayer;
    String[] roles = {"Mario","Luigi","MLG","Ghost","Solid Snake"};
    ArrayList<String> players = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout = (RelativeLayout) findViewById(R.id.MainLayout);
        usernameInput = (EditText) findViewById(R.id.nameInput);
        column = (LinearLayout) findViewById(R.id.column);
        addPlayer = (Button)this.findViewById(R.id.button1);
        addPlayer.setOnClickListener(this);
    }

    public void onClick(View v){
        if(v.equals(addPlayer)){
            if(!players.contains(usernameInput.getText().toString())&&!usernameInput.getText().toString().trim().equals("")){
                usernameInput.setText(usernameInput.getText().toString().trim());
                addNewPlayer();
                players.add(usernameInput.getText().toString());
                usernameInput.setText("");
            }
        }
    }

    public void addNewPlayer(){

        LinearLayout newline = new LinearLayout(this);
        newline.setOrientation(LinearLayout.HORIZONTAL);

        TextView playerName = new TextView(this);
        playerName.setText(usernameInput.getText());
        playerName.setTextSize(20);
        playerName.setWidth(300);
        newline.addView(playerName);

        Spinner playerRole = new Spinner(this);
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_item, roles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        playerRole.setAdapter(adapter);
        playerRole.setMinimumWidth(100);
        newline.addView(playerRole);

        Button deleteLine = new Button(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(100, 0, 0, 0);
        deleteLine.setLayoutParams(params);
        deleteLine.setText("Delete");
        deleteLine.setOnClickListener(delete(deleteLine, newline, playerName));
        newline.addView(deleteLine);


        column.addView(newline);

    }

    View.OnClickListener delete(final Button button, final LinearLayout line, final TextView name)  {
        return new View.OnClickListener() {
            public void onClick(View v) {
                players.remove(name.getText().toString());
                column.removeView(line);
            }
        };
    }
}