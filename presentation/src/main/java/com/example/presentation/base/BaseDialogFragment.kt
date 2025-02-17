package com.example.presentation.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import com.example.presentation.util.OnThrottleClickListener
import timber.log.Timber

abstract class BaseDialogFragment<T : ViewDataBinding>(@LayoutRes private val layoutResId: Int) :
    DialogFragment() {
    private var _binding: T? = null
    protected val binding: T
        get() = _binding ?: error("BaseDialogFragment binding error")

    private val tag = "${this::class.java.simpleName}"
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)

        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog?.window?.requestFeature(Window.FEATURE_NO_TITLE)
        dialog?.window?.setDimAmount(0.7f)
        binding.lifecycleOwner = viewLifecycleOwner
        logMessage("onCreateView")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        logMessage("onViewCreated")
        init()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        logMessage("onDestroyView")
        _binding = null
    }

    protected abstract fun init()
    protected fun showToast(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    protected fun logMessage(message: String) {
        Timber.tag(tag).d(message)
    }

    protected fun setDim(dim: Float){
        dialog?.window?.setDimAmount(dim)
    }

    protected fun setCancelAble(isAvailable: Boolean){
        dialog?.setCancelable(isAvailable)
    }
    protected fun View.onThrottleClick(action: (v: View) -> Unit) {
        val listener = View.OnClickListener { action(it) }
        setOnClickListener(OnThrottleClickListener(listener))
    }

}