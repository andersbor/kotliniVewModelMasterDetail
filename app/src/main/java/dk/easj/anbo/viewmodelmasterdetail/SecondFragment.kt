package dk.easj.anbo.viewmodelmasterdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import dk.easj.anbo.viewmodelmasterdetail.databinding.FragmentSecondBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val model: StudentsViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedStudent: Student? = model.selected.value
        if (selectedStudent == null) {
            binding.textviewId.text = "No student selected"
        } else {
            binding.textviewId.text = selectedStudent.id.toString()
            binding.edittextName.setText(selectedStudent.name)
            binding.edittextAddress.setText(selectedStudent.address)
            binding.edittextSemester.setText(selectedStudent.semester.toString())
            binding.edittextYearOfBirth.setText(selectedStudent.yearOfBirth.toString())
        }

        binding.buttonDelete.setOnClickListener {
            if (selectedStudent == null) {
                binding.textviewId.text = "No student selected"
            } else {
                model.remove(selectedStudent.id)
                findNavController().popBackStack()
            }
        }

        binding.buttonAdd.setOnClickListener {
            val name = binding.edittextName.text.trim().toString()
            val address = binding.edittextAddress.text.trim().toString()
            val semester = binding.edittextSemester.text.trim().toString().toInt()
            val yearOfBirth = binding.edittextYearOfBirth.text.trim().toString().toInt()
            val student = Student(name = name, address=address, semester = semester, yearOfBirth = yearOfBirth)
            model.add(student)
            findNavController().popBackStack()
        }

        /*binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }*/
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}