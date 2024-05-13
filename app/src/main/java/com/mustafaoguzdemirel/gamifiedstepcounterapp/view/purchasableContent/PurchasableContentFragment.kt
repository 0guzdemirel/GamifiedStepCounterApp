package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.purchasableContent

import android.app.Activity
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafaoguzdemirel.gamifiedstepcounterapp.R
import com.mustafaoguzdemirel.gamifiedstepcounterapp.databinding.FragmentContentBinding
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.Dataholder
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.NavigationHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.UIHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.purchasableContent.PurchasableContentModel
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.purchasableContent.PurchasableContentResponse
import com.mustafaoguzdemirel.gamifiedstepcounterapp.presenter.user.UserPresenter
import com.mustafaoguzdemirel.gamifiedstepcounterapp.presenter.user.UserView
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.adapters.purchasableContent.PurchasableContentAdapter
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.adapters.purchasableContent.PurchasableContentClickListener
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.main.MainCallback
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.purchasableContent.article.ArticleActivity
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.start.LoginActivity

class PurchasableContentFragment(private val mainCallback: MainCallback) : Fragment() {
    private var binding: FragmentContentBinding? = null
    private lateinit var userPresenter: UserPresenter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContentBinding.inflate(inflater, container, false)
        initPresenters()
        bindData()
        setListeners()
        loadContents()
        return binding?.root
    }

    private fun initPresenters() {
        userPresenter = UserPresenter()
    }

    private fun bindData() {
        binding!!.streakCountTV.text = "" + Dataholder.instance.currentUserModel?.streakCount
        binding!!.avgStepCountTV.text = "" + Dataholder.instance.currentUserModel?.avgStepCount
        binding!!.coinTV.text = "" + Dataholder.instance.currentUserModel?.coin
        binding!!.profilePhotoIV.setImageResource(UIHelper.getAvatar(Dataholder.instance.currentUserModel?.avatarId?.toInt()))
    }

    private fun setListeners() {
        binding!!.infoRL.setOnClickListener {
            mainCallback.onRanking()
        }

        binding!!.coinRL.setOnClickListener {
            mainCallback.onContent()
        }

        binding!!.logoutRL.setOnClickListener {
            activity?.finishAffinity()
            NavigationHelper.instance?.navigateToActivity(
                context = context,
                navigateActivity = LoginActivity::class.java
            )
        }
    }

    private lateinit var purchasableAdapter: PurchasableContentAdapter
    private var purchasableList: MutableList<PurchasableContentModel?>? = ArrayList()

    private fun initAdapter() {
        purchasableAdapter = PurchasableContentAdapter(
            purchasableContentClickListener = object : PurchasableContentClickListener {
                override fun onClickContent(purchasableContentModel: PurchasableContentModel?) {
                    if (purchasableContentModel?.isOwned == false) {
                        showDialogTwoButton(context = context,
                            callBack = object : ContentCallback {
                                override fun onAccept() {
                                    userPresenter.buyContent(contentId = purchasableContentModel?.id,
                                        userView = object : UserView {
                                            override fun onBuyContentSuccessful() {
                                                loadContents()
                                            }

                                            override fun onError() {

                                            }
                                        })
                                }
                            })
                    } else {
                        if (purchasableContentModel?.typeId == "3") {
                            Dataholder.instance.selectedContent = purchasableContentModel
                            NavigationHelper.instance?.navigateToActivity(
                                context = context,
                                navigateActivity = ArticleActivity::class.java
                            )
                        } else if (purchasableContentModel?.typeId == "1") {
                            openWebBrowserIntent(
                                context = requireActivity(),
                                purchasableContentModel.description
                            )
                        }
                    }
                }
            }
        )
        purchasableAdapter.setList(purchasableList)
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding?.rvList?.layoutManager = layoutManager
        binding?.rvList?.setHasFixedSize(true)
        binding?.rvList?.adapter = purchasableAdapter
        binding?.rvList?.itemAnimator = DefaultItemAnimator()
    }

    private fun loadContents() {
        userPresenter.getContents(
            userView = object : UserView {
                override fun onGetContentsSuccessful(purchasableContentResponse: PurchasableContentResponse?) {
                    purchasableList =
                        purchasableContentResponse?.purchasableContentData?.purchasableList
                    initAdapter()
                    binding?.progressBar?.visibility = View.GONE
                }

                override fun onError() {

                }
            }
        )
    }

    private fun openWebBrowserIntent(context: Activity, url: String?) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.setData(Uri.parse(url))
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        intent.setPackage("com.android.chrome")
        try {
            context.startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            intent.setPackage(null)
            context.startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private var dialog: Dialog? = null

    fun showDialogTwoButton(
        context: Context?,
        callBack: ContentCallback
    ) {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            if (context != null) {
                dialog = Dialog(context)
                dialog!!.setContentView(R.layout.dialog_yes_no)
                dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                dialog!!.setCanceledOnTouchOutside(true)

                val noButtonL = dialog!!.findViewById<RelativeLayout>(R.id.noBtnL)
                val yesButtonL = dialog!!.findViewById<RelativeLayout>(R.id.yesBtnL)

                noButtonL.setOnClickListener { v: View? ->
                    dialog!!.dismiss()
                }

                yesButtonL.setOnClickListener { v: View? ->
                    callBack.onAccept()
                    dialog!!.dismiss()
                }

                try {
                    dialog!!.show()
                } catch (e: WindowManager.BadTokenException) {
                    //use a log message
                }
            }
        }, 0)
    }

}