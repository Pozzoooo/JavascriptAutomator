package pozzo.apps.javascriptautomator.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.List;

import pozzo.apps.javascriptautomator.R;
import pozzo.apps.javascriptautomator.model.Suggestion;

/**
 * Adapter for suggestions lists.
 *
 * @author Luiz Gustavo Pozzo
 * @since 07/02/16
 */
public class SuggestionAdapter extends RecyclerView.Adapter<SuggestionAdapter.ViewHolder> {
	private List<Suggestion> suggestions;
	private SuggestionInteraction callback;

	public SuggestionAdapter(List<Suggestion> suggestions, SuggestionInteraction callback) {
		this.suggestions = suggestions;
		this.callback = callback;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.adapter_suggestion, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Suggestion suggestion = suggestions.get(position);
		holder.bSuggetion.setText(suggestion.getDisplayName());
		holder.bSuggetion.setTag(suggestion);
	}

	@Override
	public int getItemCount() {
		return suggestions.size();
	}

	/**
	 * Click event.
	 */
	private View.OnClickListener onSuggestionClick = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			if(callback != null)
				callback.onSuggestionClick((Suggestion) v.getTag());
		}
	};

	/**
	 * To receive the click events.
	 */
	public interface SuggestionInteraction {
		void onSuggestionClick(Suggestion suggestion);
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		private Button bSuggetion;

		public ViewHolder(View itemView) {
			super(itemView);

			bSuggetion = (Button) itemView.findViewById(R.id.bSuggestion);
			bSuggetion.setOnClickListener(onSuggestionClick);
		}
	}
}
