package dk.easj.anbo.viewmodelmasterdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dk.easj.anbo.viewmodelmasterdetail.databinding.FragmentFirstBinding

class StudentListFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val viewModel: StudentsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // configure RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        // https://medium.com/geekculture/everything-you-should-know-to-create-a-recyclerview-3defdb660a2f
        // binding.recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, true)
        // binding.recyclerView.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        val students: List<Student> = viewModel.students.value!!
        val adapter = StudentsAdapter(students) { position ->
            viewModel.selected.value = viewModel[position]
            viewModel.adding.value = false
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}