package com.android.kpe.books;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by LocalUser on 23/09/2017.
 */

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {

    private ArrayList<Book> mBooks = null;

    public BooksAdapter(ArrayList<Book> books) {
        this.mBooks = books;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.book_list_item, parent, false);
        return new BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        Book book = mBooks.get(position);
        holder.bind(book);
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder {

        TextView mAuthors = null;
        TextView mPublishedDate = null;
        TextView mPublisher = null;
        TextView mTitle = null;

        public BookViewHolder(View itemView) {
            super(itemView);
            mAuthors = (TextView) itemView.findViewById(R.id.txtAuthors);
            mPublishedDate = (TextView) itemView.findViewById(R.id.txtPublishedDate);
            mPublisher = (TextView) itemView.findViewById(R.id.txtPublisher);
            mTitle = (TextView) itemView.findViewById(R.id.txtTitle);
        }

        public void bind (Book book) {
            mTitle.setText(book.title);
            mPublishedDate.setText(book.publishedDate);
            mPublisher.setText(book.publisher);

            StringBuilder sb = new StringBuilder();
            for(int index=0; index < book.authors.length; index++) {
                sb.append(book.authors[index]);
                if(index < book.authors.length -1) {
                    sb.append(", ");
                }
            }
            mAuthors.setText(sb.toString());

        }

    }

}
