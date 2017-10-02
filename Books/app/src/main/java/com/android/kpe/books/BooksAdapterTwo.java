package com.android.kpe.books;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by LocalUser on 24/09/2017.
 */

public class BooksAdapterTwo extends RecyclerView.Adapter<BooksAdapterTwo.BookViewHolder> {

    private ArrayList<Book> mBooks = null;
    public BooksAdapterTwo(ArrayList<Book> books) {
        mBooks = books;
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.book_list_row, parent, false);
        return new BooksAdapterTwo.BookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        holder.bind(mBooks.get(position));
    }

    @Override
    public int getItemCount() {
        return mBooks.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public BookViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        public void bind(Book book) {
            setText(R.id.txtTitle, book.title);
            setText(R.id.txtPublisher, book.publisher);
            setText(R.id.txtPublishedDate, book.publishedDate);
            setText(R.id.txtAuthors, book.authors);
            setText(R.id.txtDescription, book.description);
        }

        private void setText(int id, String text) {
            TextView textView = (TextView) itemView.findViewById(id);
            if(textView != null) {
                textView.setText(text == null ? "" : text);
            }
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Book selectedBook = mBooks.get(position);
            Intent intent = new Intent(itemView.getContext(), BookDetail.class);
            intent.putExtra("Book", selectedBook);
            itemView.getContext().startActivity(intent);
        }
    }
}
