package com.example.receipe_recyclerview;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    //Class Variables
    private final LinkedList<Recipe> mRecipes;
    private final LayoutInflater mInflater;
    private Context context;
    public int position;

    class RecipeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        //Class Variables
        public final TextView title;
        public final TextView description;
        private RecipeAdapter adapter;
        private final CardView card;


        public RecipeViewHolder(View itemView, RecipeAdapter adapter) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.title);
            description = (TextView)itemView.findViewById(R.id.description);
            card = itemView.findViewById(R.id.cv);
            itemView.setOnClickListener(this);
            this.adapter = adapter;

        }


        @Override
        public void onClick(View view) {
            position = getAdapterPosition();
            Intent intent = new Intent(context, RecipePage.class);
            String pos = Integer.toString(position);
            intent.putExtra(MainActivity.EXTRA_REPLY,pos);
            context.startActivity(intent);

        }
    }


    public RecipeAdapter(Context context, LinkedList<Recipe> recipes){
        mInflater = LayoutInflater.from(context);
        this.mRecipes = recipes;
        this.context = context;
    }

    @Override
    public RecipeAdapter.RecipeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View mItemView = mInflater.inflate(R.layout.recipe, viewGroup, false);
        return new RecipeViewHolder(mItemView, this);
    }


    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder recipeViewHolder, int i) {
        Recipe recipe = mRecipes.get(i);
        recipeViewHolder.title.setText(recipe.name);
        recipeViewHolder.description.setText(recipe.description);
    }


    @Override
    public int getItemCount() {
        return mRecipes.size();
    }
}
