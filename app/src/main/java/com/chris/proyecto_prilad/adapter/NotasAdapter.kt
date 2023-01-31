import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.chris.proyecto_prilad.R
import com.chris.proyecto_prilad.model.Notas
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions

class NotasAdapter(options: FirestoreRecyclerOptions<Notas>) : FirestoreRecyclerAdapter<Notas, NotasAdapter.ViewHolder>(options) {
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int, notas: Notas) {
        viewHolder.fecha.text = notas.fecha
        viewHolder.materia.text = notas.materia
        viewHolder.tarea.text = notas.tarea
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.view_notas_single, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val fecha: TextView = itemView.findViewById(R.id.id_Fecha)
        val materia: TextView = itemView.findViewById(R.id.id_input_materia)
        val tarea: TextView = itemView.findViewById(R.id.id_input_tareas)
    }
}
