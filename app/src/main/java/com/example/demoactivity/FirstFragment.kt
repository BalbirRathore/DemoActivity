package com.example.demoactivity


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FirstFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FirstFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private val lazy by lazy {
        //AppLogger.log("lazy init")
        throw RuntimeException("lazy init")
        "lazy"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        try {
            //AppLogger.log("lazy==$lazy")
        } catch (e: Exception) {
        }


        try {
            //AppLogger.log("lazy1==$lazy")
        } catch (e: Exception) {
        }

        /* AppLogger.log("lazy==$lazy")
         AppLogger.log("lazy1==$lazy")*/
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val button = view.findViewById<Button>(R.id.button)
        button.setOnClickListener {
            //findNavController().navigate(R.id.actionFirstFragmentToSecondFragment)
            val navOptions = NavOptions.Builder().setPopUpTo(R.id.firstFragment, true).build()
            findNavController().navigate(
                FirstFragmentDirections.actionFirstFragmentToSecondFragment(),
                navOptions
            )
        }
        lifecycleScope.launch {
            testCoroutine()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FirstFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FirstFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onDestroy() {
        super.onDestroy()
        //AppLogger.log("FirstFragment onDestroy")
    }

    override fun onDestroyView() {
        super.onDestroyView()
       // AppLogger.log("FirstFragment onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        //AppLogger.log("FirstFragment onDetach")
    }

    override fun onPause() {
        super.onPause()
        //AppLogger.log("FirstFragment onPause")
    }

    override fun onStop() {
        super.onStop()
       // AppLogger.log("FirstFragment onStop")
    }

    override fun onStart() {
        super.onStart()
       // AppLogger.log("FirstFragment onStart")
    }

    private val scope = CoroutineScope(CoroutineName("ParentCoroutine"))
    private suspend fun testCoroutine() {
        //AppLogger.log("test")
       val mainJob =scope.launch {
            val job1 = launch {
                while (true) {
                    AppLogger.log("first child coroutine")
                    //ensureActive()
                    //delay(5000)
                    //Thread.sleep(5000)
                }
            }
            val job2 = launch {
                AppLogger.log("second child coroutine")
            }
         /*  AppLogger.log("second child coroutine is going cancel")
            delay(5)
            job2.cancelAndJoin()
           AppLogger.log("second child coroutine has been canceled")*/
        }
        AppLogger.log("main coroutine is going cancel")
        delay(50)
        mainJob.cancelAndJoin()
        AppLogger.log("main coroutine has been canceled")
    }
}
