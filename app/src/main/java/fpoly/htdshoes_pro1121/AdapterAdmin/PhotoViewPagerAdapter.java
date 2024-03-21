package fpoly.htdshoes_pro1121.AdapterAdmin;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

import fpoly.htdshoes_pro1121.Model.Photo;
import fpoly.htdshoes_pro1121.R;

public class PhotoViewPagerAdapter extends PagerAdapter {
    private List<Photo> mlisstPhoto;

    public PhotoViewPagerAdapter(List<Photo> mlisstPhoto) {
        this.mlisstPhoto = mlisstPhoto;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.listphoto,container,false);
        ImageView imgphoto = view.findViewById(R.id.img_photo);
        Photo photo = mlisstPhoto.get(position);
        imgphoto.setImageResource(photo.getResourceID());

        // add View
        container.addView(view);


        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {


        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        if (mlisstPhoto != null){
            return mlisstPhoto.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {

        return view == object;
    }
}
