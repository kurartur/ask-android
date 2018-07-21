package lt.dualpair.test.ask.android.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import lt.dualpair.test.ask.android.R;
import lt.dualpair.test.ask.android.data.entity.Question;

public class QuestionsRecyclerAdapter extends RecyclerView.Adapter<QuestionsRecyclerAdapter.ViewHolder> {

    private List<Question> questions;
    private OnItemClickListener onItemClickListener;

    public QuestionsRecyclerAdapter(List<Question> questions, OnItemClickListener onItemClickListener) {
        this.questions = questions;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_question_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question question = questions.get(position);
        holder.setQuestion(question, onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return questions.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView subject;
        private TextView text;
        private View menu;

        public ViewHolder(final View itemView) {
            super(itemView);
            subject = itemView.findViewById(R.id.subject);
            text = itemView.findViewById(R.id.text);
            menu = itemView.findViewById(R.id.menu);

        }

        public void setQuestion(Question question, OnItemClickListener onItemClickListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onClick(question);
                }
            });
            menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final PopupMenu popup = new PopupMenu(itemView.getContext(), menu);
                    popup.getMenuInflater().inflate(R.menu.question_item_menu, popup.getMenu());
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        public boolean onMenuItemClick(MenuItem item) {
                            int i = item.getItemId();
                            if (i == R.id.not_interested) {
                                Toast.makeText(itemView.getContext(), "TODO", Toast.LENGTH_LONG).show();
                                return true;
                            }
                            return false;
                        }
                    });
                    popup.show();
                }
            });
            subject.setText(question.getSubject());
            text.setText(question.getText());
        }
    }

    public interface OnItemClickListener {
        void onClick(Question question);
    }
}
