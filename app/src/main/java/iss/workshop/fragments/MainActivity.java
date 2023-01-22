package iss.workshop.fragments;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity implements
        ListFragment.IListFragment, DetailFragment.IDetailFragment {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public void viewDetail(int itemId) {
        View fragmentContainer = findViewById(R.id.fragment_container);
        if (fragmentContainer != null) {
            // Add the fragment to the FrameLayout
            replaceDetailFragment(itemId);
        } else {
            startDetailActivity(itemId);
        }
    }
    void startDetailActivity(int newItemId) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("itemId", newItemId);
        startActivity(intent);
    }
    public void replaceDetailFragment(int newItemId) {
        Bundle arguments = new Bundle();
        arguments.putInt("itemId", newItemId);
        DetailFragment fragment = new DetailFragment();
        fragment.setArguments(arguments);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction trans = fm.beginTransaction();
        trans.replace(R.id.fragment_container, fragment);
        trans.addToBackStack(null);
        trans.commit();
    }
    @Override
    public void itemClicked(String content) {
        Toast.makeText(this, content, Toast.LENGTH_LONG).show();
    }
}

