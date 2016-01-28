package pozzo.apps.javascriptautomator.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pozzo.apps.javascriptautomator.R;
import pozzo.apps.javascriptautomator.model.Entry;

/**
 * To list our entries.
 *
 * @author Luiz Gustavo Pozzo
 * @since 21/01/16.
 */
public class EntryAdapter extends RecyclerView.Adapter<EntryAdapter.ViewHolder> {
	private List<Entry> entries;
	private View.OnClickListener onEntryClick;
	private View.OnLongClickListener onLongEntryClick;

	public EntryAdapter(View.OnClickListener onEntryClick,
						View.OnLongClickListener onLongEntryClick) {
		super();
		this.onEntryClick = onEntryClick;
		this.onLongEntryClick = onLongEntryClick;
	}

	public void setEntries(List<Entry> entries) {
		this.entries = entries;
		notifyDataSetChanged();
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.adapter_entry, parent, false);
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		Entry entry = entries.get(position);

		holder.vgMain.setTag(entry);
		holder.vgMain.setOnClickListener(onEntryClick);
		holder.vgMain.setOnLongClickListener(onLongEntryClick);
		holder.lAddress.setText(entry.getAddress());
		holder.lName.setText(entry.getName());
	}

	@Override
	public int getItemCount() {
		return entries != null ? entries.size() : 0;
	}

	public class ViewHolder extends RecyclerView.ViewHolder {
		TextView lAddress;
		TextView lName;
		ViewGroup vgMain;

		public ViewHolder(View itemView) {
			super(itemView);

			vgMain = (ViewGroup) itemView.findViewById(R.id.vgMain);
			lName = (TextView) itemView.findViewById(R.id.lName);
			lAddress = (TextView) itemView.findViewById(R.id.lAddress);
		}
	}
}
