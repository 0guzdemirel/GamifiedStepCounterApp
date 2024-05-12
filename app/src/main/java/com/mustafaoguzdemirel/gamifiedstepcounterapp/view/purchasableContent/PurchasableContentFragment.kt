package com.mustafaoguzdemirel.gamifiedstepcounterapp.view.purchasableContent

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.mustafaoguzdemirel.gamifiedstepcounterapp.databinding.FragmentContentBinding
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.Dataholder
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.NavigationHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.helper.UIHelper
import com.mustafaoguzdemirel.gamifiedstepcounterapp.model.purchasableContent.PurchasableContentModel
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.adapters.purchasableContent.PurchasableContentAdapter
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.adapters.purchasableContent.PurchasableContentClickListener
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.main.MainCallback
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.purchasableContent.article.ArticleActivity
import com.mustafaoguzdemirel.gamifiedstepcounterapp.view.start.LoginActivity

class PurchasableContentFragment(private val mainCallback: MainCallback) : Fragment() {
    private var binding: FragmentContentBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentContentBinding.inflate(inflater, container, false)
        bindData()
        setListeners()
        initData()
        return binding?.root
    }

    private fun bindData() {
        binding!!.streakCountTV.text = "" + Dataholder.instance.currentUserModel?.streakCount
        binding!!.avgStepCountTV.text = "" + Dataholder.instance.currentUserModel?.avgStepCount
        binding!!.coinTV.text = "" + Dataholder.instance.currentUserModel?.coin
        binding!!.profilePhotoIV.setImageResource(UIHelper.getAvatar(Dataholder.instance.currentUserModel?.avatarId))
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
    private var purchasableList: MutableList<PurchasableContentModel> = ArrayList()

    private fun initAdapter() {
        purchasableAdapter = PurchasableContentAdapter(
            purchasableContentClickListener = object : PurchasableContentClickListener {
                override fun onClickContent(purchasableContentModel: PurchasableContentModel) {
                    if (purchasableContentModel.typeId == "3") {
                        Dataholder.instance.selectedContent = purchasableContentModel
                        NavigationHelper.instance?.navigateToActivity(
                            context = context,
                            navigateActivity = ArticleActivity::class.java
                        )
                    } else if (purchasableContentModel.typeId == "1") {
                        openWebBrowserIntent(
                            context = requireActivity(),
                            purchasableContentModel.description
                        )
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

    private fun initData() {
        purchasableList = mutableListOf(
            PurchasableContentModel(
                id = "1",
                typeId = "1",
                price = 150,
                title = "yürüyüş yaparken dinlenecek şarkılar",
                description = "https://open.spotify.com/playlist/6e3JAWeWqH0avmvrwkvBjN?si=4dabe9ce0d564634",
                duration = 0,
                url = "https://art.ngfiles.com/images/2358000/2358119_dgreeng_spongebob-patrick-running.png?f1645064045",
                isOwned = false
            ),

            PurchasableContentModel(
                id = "2",
                typeId = "3",
                price = 50,
                title = "Her gün kaç adım atmayı hedeflemelisiniz?",
                description = "Her gün atmanız gereken adım sayısı bireysel sağlık hedeflerinize ve aktivite seviyelerinize bağlıdır. Yürüyüş aktivitesine yeni başlıyorsanız günde 5 bin adım hedefiyle başlayabilirsiniz. Bu hedefe ulaştığınızda, adım sayınızı kademeli olarak haftada 500 ila 1.000 adıma kadar artırabilirsiniz.\n" +
                        "\n" +
                        "Kilo vermek veya kronik hastalık riskinizi azaltmak gibi spesifik sağlık hedefleriniz varsa daha yüksek adım sayısını, medikal danışmanınız veya doktorunuzla belirleyeceğiniz bir hedefe çekmeniz gerekebilir. Birçok çalışma, günde 10 binden az adım atmanın sağlık açısından fayda sağlayacağını öne sürüyor. Araştırmalar, günde 5 bin ila 7 bin adım kadar az adımın bile kardiyovasküler sağlığın, kilo yönetiminin ve genel refahın iyileşmesine yol açabileceğini söylüyor.\n" +
                        "\n" +
                        "\u200B\u200BGerekli adım sayısı yaş gruplarına göre farklılık gösterebilir. Yaşlı yetişkinler veya hareket etme sorunları olan kişilerin 10 bin adıma ulaşması kolay olmayabilir. Ancak, bu kişiler için bu sayının yarısı kadar yürümek bile yeterli faydayı görmelerini sağlayabilir. Bu nedenle yaşınıza ve yeteneklerinize göre ulaşılabilir gerçekçi hedefler belirlemek çok önemlidir.\n" +
                        "\n" +
                        "Doğrudan 10 bin adımı hedeflemek yerine, ulaşılabilir adımlarla ilerleyin. Örneğin, şu anda günde 3 bin adım atıyorsanız 5 bin adım atmayı hedefleyin ve oradan yavaş yavaş artırın. Tutarlılık, sürdürülebilir bir fiziksel aktivite rutini oluşturmanın anahtarıdır. Her gün 10 bin adıma ulaşamasanız bile sürekli olarak aktif olmaya özen gösterin. Bu, günlük yürüyüşleri, merdivenleri kullanmayı veya diğer egzersiz türlerini de içerebilir. Vücudunuzun verdiği sinyallere dikkat edin. Ağrı, yorgunluk veya rahatsızlık hissederseniz rutininizi değiştirmeniz ve gerekirse bir sağlık uzmanına başvurmanız önem taşır.\n" +
                        "\n" +
                        "Sonuç olarak, en önemlisi sürdürülebilir, keyifli ve yaşınıza, zindelik seviyenize ve yaşam tarzınıza uygun bir denge bulmaktır. Belirli bir sayıya odaklanmak yerine, fiziksel aktivitenizin kalitesine ve bunun genel sağlığınız ve refahınız üzerindeki etkisine odaklanın. Sonuçta önemli olan aktif olmak, tutarlı kalmak ve hedeflerinizi benzersiz ihtiyaçlarınıza ve yeteneklerinize göre uyarlamaktır. 10 bin adım hedefi popüler bir fitness hedefi ancak herkes için gerekli değil. Her gün kaç adım atmanız gerektiğini belirlemenin en iyi yolu, doktorunuzla konuşmak ve bireysel sağlık hedeflerinizi ve aktivite düzeyinizi dikkate almaktır.\n" +
                        "\n",
                duration = 0,
                url = "https://www.yapikredi.com.tr/medium/image/gunluk-hayatta-adim-sayisi-10000-adim-miti-ve-gercekleri_68290/view",
                isOwned = false
            ),

            PurchasableContentModel(
                id = "3",
                typeId = "1",
                price = 100,
                title = "autumn playlist \uD83C\uDF42",
                description = "https://open.spotify.com/playlist/18hb9U4tahrjKSBH3sOi74?si=a5f84134d09d4f07",
                duration = 0,
                url = "https://cdn.britannica.com/88/137188-050-8C779D64/Boston-Public-Garden.jpg",
                isOwned = false
            ),

            PurchasableContentModel(
                id = "4",
                typeId = "3",
                price = 20,
                title = "Sağlıklı beslenme nasıl olur?",
                description = "Sağlıklı beslenme, hem sağlığın hem de vücut formunun korunması açısından en önemli konulardan bir tanesidir. Bebeklerde anne sütü veya formülalar ile başlayan süreçten, yetişkinlik ve yaşlılık dönemine kadar tüm yaş gruplarında sağlıklı ve dengeli beslenme konusunda gerekliliklerin yerine getirilmesi, hastalıklardan korunmak ve ideal vücut kütlesine sahip olmak açısından büyük önem taşır. Vücutta günlük işlevlerin sorunsuz bir şekilde yerine getirilebilmesi için tüm besin ögelerinin eksiksiz bir şekilde karşılanması gerekir. Bunun sağlanabilmesi için sağlıklı ve dengeli beslenme elzemdir. Ek olarak her yaştan birey için obezite, akut ve kronik birçok hastalık türü için en önemli risk faktörleri arasında yer alır. Dolayısıyla sağlıklı beslenme ve enerji ihtiyacına uygun kalori alımı ile vücut kütlesinin ideal aralıkta tutulması, hastalıklardan korunmak ve sağlıklı bir yaşam sürebilmek adına oldukça önemlidir. Tüm bu nedenlerden dolayı, bebeklikten yaşlılığa kadar insan ömrünün her döneminde sağlıklı beslenme konusunda gerekli özen gösterilmeli ve bu konuda bilinçli davranmaya gayret edilmelidir.",
                duration = 0,
                url = "https://www.medicalpark.com.tr/_uploads/_images/_healthGuide/3UNc5356.jpg",
                isOwned = false
            ),

            )
        initAdapter()
    }

    fun openWebBrowserIntent(context: Activity, url: String?) {
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

}