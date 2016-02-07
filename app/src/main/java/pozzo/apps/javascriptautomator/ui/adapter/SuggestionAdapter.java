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

	public SuggestionAdapter(List<Suggestion> suggestions) {
		this.suggestions = suggestions;
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
	}

	@Override
	public int getItemCount() {
		return suggestions.size();
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		private Button bSuggetion;

		public ViewHolder(View itemView) {
			super(itemView);

			bSuggetion = (Button) itemView.findViewById(R.id.bSuggestion);
		}
	}
}
