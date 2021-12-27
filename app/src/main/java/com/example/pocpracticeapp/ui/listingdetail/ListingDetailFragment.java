package com.example.pocpracticeapp.ui.listingdetail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.imageloader.ImageLoader;
import com.example.pocpracticeapp.databinding.CharacterDetailFragmentBinding;
import com.example.pocpracticeapp.domain.model.ResultsItem;
import com.google.gson.Gson;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class ListingDetailFragment extends Fragment {

    private CharacterDetailFragmentBinding binding = null;
    private ResultsItem item = null;

    @Inject
    ImageLoader imageLoader;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.binding = CharacterDetailFragmentBinding.inflate(inflater, container, false);
        return (View)this.binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        item  =  new Gson().fromJson(getArguments().getString("listing"), ResultsItem.class);
        bindCharacter(item);
    }



    private void bindCharacter(ResultsItem listing) {
        binding.name.setText(listing.getName());
        binding.species.setText(listing.getPrice());
        binding.status.setText(listing.getCreatedAt());
        imageLoader.displayImage(listing.getImageUrls().get(0), binding.image);
    }
}
