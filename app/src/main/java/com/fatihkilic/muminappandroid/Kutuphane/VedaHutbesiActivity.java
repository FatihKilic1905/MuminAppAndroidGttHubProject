package com.fatihkilic.muminappandroid.Kutuphane;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.applovin.mediation.ads.MaxAdView;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkConfiguration;
import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityAyarlarBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityVedaHutbesiBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class VedaHutbesiActivity extends AppCompatActivity {

    private ActivityVedaHutbesiBinding binding;
    private MaxAdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veda_hutbesi);

        binding = ActivityVedaHutbesiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().setTitle("Veda Hutbesi");

        AppLovinSdk.getInstance( this ).setMediationProvider( "max" );
        AppLovinSdk.initializeSdk( this, new AppLovinSdk.SdkInitializationListener() {
            @Override
            public void onSdkInitialized(final AppLovinSdkConfiguration configuration)
            {

                createBannerAd();

            }
        } );




        TextView vedaHutbesiText = binding.vedaHutbesiText;
        vedaHutbesiText.setText("Allah'a hamd olsun. O'nu över, O'na şükrederiz. O'ndan medet umarız. O'ndan bağışlanma dileriz, tevbe ederek O'na ita\u00ADate yöneliriz. Nefislerimizin kötülük telkin\u00ADlerinden ve kötü ameller işlemesinden Al\u00ADlah'a sığınırız. Allah kime doğruyu göste\u00ADrirse, kimse onu hak yoldan uzaklaştıra\u00ADmaz. Kimin de hak yoldan uzaklaşmasına özgürlük tanırsa, kimse ona doğruyu gös\u00ADteremez. Tek Allah'tan başka tanrı olma\u00ADdığını, ilahlığında, otoritesinde, mülkün\u00ADde, tasarruflarında ortağı bulunmadığını kabul ve tasdik ederim. Muhammed'in O'nun kulu ve Rasûlü olduğunu kabul ve tasdik ederim. (1)\n" +
                "\n" +
                "Ey Allah'ın kulları, size Allah'a sığın\u00ADmanızı, emirlerine yapışmanızı, günahlar\u00ADdan arınmanızı, azabından korunmanızı öğütlerim. Size tekrar tekrar, O'na itaati tavsiye ederim. Sözlerime hayırlı olanla, O'nun izni ve yardımıyla başlıyorum. (2)\n" +
                "\n" +
                "Ey İnsanlar! Ben sizin hepinize, Al\u00ADlah'ın; emirlerini tebliğ ile görevlendirdiği, ilahî hükümleri icraya, ülkeyi imara, dünya düzenini kurmaya, sağlamaya memur et\u00ADtiği tek yetkili Rasûlüyüm. Beni dinleyin, size bazı açıklamalar yapacağım. Bu yıldan sonra, bir daha burada sizinle buluşup buluşamayacağımı bilemiyorum. (3)\n" +
                "\n" +
                "Ey İnsanlar! Kanlarınız, canlarınız, yaşa\u00ADma hakkınız, mallarınız, namuslarınız, haysiyet ve şerefleriniz, vücut bütünlü\u00ADğünüz Rabbinizle buluşacağınız güne ka\u00ADdar bu ayınızda, bu beldenizde, bu günü\u00ADnüzün saygıya, korunmaya layık olduğu gi\u00ADbi, saygıya ve korunmaya layıktır, doku\u00ADnulmazdır. Ancak İslam'ın koyduğu sorumluluk gereği uygulanan gerekçeli kara\u00ADra dayalı cezalar müstesnadır. (4)\n" +
                "\n" +
                "Benim sözlerimi iyi dinleyin ki, izzet ve şerefle huzurlu yaşamaya devam edesiniz. Sakın haksızlık yapmayın ve zulmetmeyin. Sakın baskı, zulüm ve işkenceye alet olmayın. Sakın zulme boyun eğmeyin. Haksızlığa rı\u00ADza göstermeyin. İyice anlatabildim mi?\n" +
                "\n" +
                "Allah'ım, Sen de şahit ol. (5)\n" +
                "\n" +
                "Ashabım! Siz Rabbinizin huzuruna vara\u00ADcaksınız, size işlediğiniz bilinçli amellerin hesabını sorulacak. İyice tebliğ edebildim mi?\n" +
                "\n" +
                "Allah'ım, Sen de şahit ol! (6)\n" +
                "\n" +
                "Ey İnsanlar, Allah'a sığının, emirlerine yapışın, azabından korunun. İnsanların mallarını eksik teslim etmeyin, değerlerini düşürmeyin, bedellerini eksik ödemeyin, mallarını kötülemeyin, haksız rekabet yap\u00ADmayın, aldatarak, hile yaparak, fırsat kollayarak, gasp ederek insanların haklarını zayi etmeyin, zayiine sebep olmayın. Ül\u00ADkede, yeryüzünde bozgunculuk yaparak karışıklık çıkarmakta ve küfürde ileri git\u00ADmeyin. (7)\n" +
                "\n" +
                "Ashabım! Kimin yanında bir emanet varsa, bu emaneti sahibine versin. Size hediye verene hediye ile karşılık verin. Kefil borçlu gibidir. Borcun ödenmesi gerekir. (8)\n" +
                "\n" +
                "Soyunuzdan sopunuzdan medet umarak benim yanıma yaklaşmayın. İşlediğiniz bi\u00ADlinçli amelleri vesile ederek yanıma gelin. Ben bütün insanlara da, size de aynı şey\u00ADleri söylüyorum. (9)   \n" +
                "\n" +
                "Cahiliye döneminin faizli alışverişleri kaldırılmıştır. Yüce Allah, kaldırılan ilk fa\u00ADizin,  Abbas b. Abdilmuttalib'inki olmasını emretmiştir. Ancak ana paralarınız sizindir. Ne siz haksızlık edebilirsiniz, ne de haksız\u00ADlığa uğratılacaksınız. Allah, faizli alışverişin yapılmayacağını icrası kesin hüküm haline getirdi. Kaldıracağım ilk faiz amcam Ab\u00ADbas b. Abdilmuttalib'in faizli alış verişlerindeki faizdir. (10)\n" +
                "\n" +
                "Ey İnsanlar! Hangi ayda, hangi günde, hangi ülkede olduğunuzu biliyor musu\u00ADnuz? (11)\n" +
                "\n" +
                "(İnsanlar, ‘saygıya layık korunan bir günde, dokunulmazlığı olan ülkede ve bir ayda', dediler.)\n" +
                "\n" +
                "Ey İnsanlar! Kanlarınız, canlarınız, yaşa\u00ADma hakkınız, mallarınız, namuslarınız, haysiyet ve şerefleriniz, vücut bütünlüğü\u00ADnüz, Rabbinizle buluşacağınız güne kadar bu ayınızda, bu beldenizde, bu gününüzün saygıya, korunmaya layık olduğu gibi, saygıya ve korunmaya layıktır, dokunul\u00ADmazdır. Ancak İslam'ın koyduğu sorumluluk gereği uygulanan gerekçeli karara da\u00ADyalı cezalar müstesnadır. (12)\n" +
                "\n" +
                "Ashabım! Şunu belirteyim ki, Cahiliye dönemindeki bütün kan, su ve mal dava\u00ADları, kıyamet gününe kadar şu ayaklarımın altındadır. (13)   \n" +
                "\n" +
                " Kıyamet gününe kadar Cahiliye döneminde var olan kan da\u00ADvaları kaldırılmıştır, Cahiliye döneminde var olan kan davaları kaldırılmıştır, kaldıracağımız ilk kan davası, Amir (İyâs) b. Rebîa b. el-Hâris b. Abdülmuttalib'in kan davasıdır. O Sa'd b. Leysoğulları'nda süt anneye verilmiş bir çocuktu. Hüzeyl, onu öldürdü.\n" +
                "\n" +
                "İyice tebliğ edebildim mi?\n" +
                "\n" +
                "(İnsanlar; ‘elbette tebliğ ettin', dediler)\n" +
                "\n" +
                "-Allah'ım Sen de şahit ol! Burada bulu\u00ADnanlar sözlerimi bulunmayanlara iletsin. (14)   \n" +
                "\n" +
                "Kâbe hizmetkarlığı ve hacıların su ihtiya\u00ADcını karşılama dışında cahiliye devrinin hükümet görevleri kaldırılmıştır. (15)   \n" +
                "\n" +
                "Kasten adam öldürmenin cezası, kısas\u00ADtır. Kasten öldürmeye benzeyen cinayet, sopa ve taşla öldürmedir. Diyeti, yüz deve\u00ADdir. Kim daha fazlasını isterse, o İslam'ı benimsemeyen Cahiliye dönemini özleyen biridir. En büyük Allah düşmanı, kendisine herhangi bir kastı olmayan birini sebepsiz yere öldürendir, kendisine el kaldırmayana sebepsiz yere vurandır.\n" +
                "\n" +
                "İyice tebliğ edebildim? Allah'ım, Sen de şahit ol! (16)\n" +
                "\n" +
                "Ey İnsanlar! Sizi uyarıyorum, herkes yal\u00ADnızca kendi işlediği suçtan sorumludur. Suçlu evlattan dolayı baba sorumlu tutula\u00ADmaz, suçlu babadan dolayı evlat da sorum\u00ADlu tutulamaz. (17)\n" +
                "\n" +
                "Ey İnsanlar! Şeytan, sizin bu toprakla\u00ADrınızda kendisine tapınılmasından ümit kesmiş bulunuyor. Ancak, bunun dışındaki önemsiz gördüğünüz davranışlarda, ara\u00ADnızda çıkardığı fitne fesatla sizi birbirinize düşürdüğünde   sözünün   dinlenmesinden hoşnut olacaktır. Dininizde sebat ederek, dininize sahip çıkarak, şeytanın, şeytan tıynetli ahlaksız azgınların, şeytani düzen\u00ADlerin vesvesesinden, daleveresinden kendi\u00ADnizi koruyun. (18)  \n" +
                "\n" +
                "Ey İnsanlar, yalan yere Allah'ın adını anarak yemin etmeyin. Yalan yere Allah adına yemin edenin yalanını Allah açığa çıkarır. (19)\n" +
                "\n" +
                "Ey İnsanlar! Zaman, Allah'ın gökleri ve yeri yarattığı gündeki düzenli sistemine girerek seyrediyor. Ayların sayısı, on ikidir. Dört tanesi, savaşın haram olduğu aylar\u00ADdır. Bunlardan üçü birbiri peşinden gelir. Biri tektir. Bunlar Zilkade, Zilhicce, Mu\u00ADharrem ve Cumade'l-ahire ile Şaban ara\u00ADsındaki Mudar kabilesinin adını koyduğu ay Recep'tir.\n" +
                "\n" +
                "Allah'ın, gökleri ve yeri yarattığı gün, Levh-i Mahfuz'da tesbit ettiği kayıtlarda, Allah katında, ayların sayısı on ikidir. On iki aydan dördü savaşın haram olduğu ay\u00ADlardır. İşte bu haram aylarla ilgili hüküm, insanlığı, insani değerleri ve düzeni ayakta tutan dinin, medeniyetin, zamanla değiş\u00ADmeyen tabii hukuk kurallarını içeren şe\u00ADriatın hükmüdür. Bu aylarla ilgili Allah'ın koyduğu yasakları çiğneyerek kendinize, birbirinize zulmetmeyin.\n" +
                "\n" +
                "İlahlığında, otoritesinde, mülkünde, ta\u00ADsarruflarında, Allah'a ortak koşan Müşrik\u00ADler nasıl size karşı topyekün savaşıyorlarsa, siz de onlara karşı topyekün savaşın. Bilin ki, Allah kendisine sığınıp, emirlerine ya\u00ADpışarak günahlardan arınıp, azaptan koru\u00ADnanlarla,  kulluk ve sorumluluk şuuruyla, haklarına ve özgürlüklerine sahip çıkarak şahsiyetli davranan, dinî ve sosyal görev\u00ADlerinin bilincinde olan müminlerle, müttakilerle beraberdir.\n" +
                "\n" +
                "Saldırmazlığın gelenek haline geldiği, Al\u00ADlah'ın savaşı haram kıldığı ayları erteleyerek, yerlerini değiştirerek, on iki aya ay ilave ederek, hileli takvim düzenlemek, ke\u00ADsinlikle Allah'ın sene ve aylarla ilgili koy\u00ADduğu hükmü inkarda ileri gitmektir. Kulluk sözleşmesindeki ortak taahhütlerini, Al\u00ADlah'a iman, kulluk ve sorumluluk bilincini şuur altına iterek örtbas edip inkarda ısrar edenlerin, kafirlerin, bu yüzden hak yol\u00ADdan uzaklaşmalarının, dalaleti tercihlerinin önü açılır. Erteleyerek, değiştirerek ilave ettikleri aydaki savaşları, bir yıl helal ve meşru, bir yıl haram sayarlar. Allah'ın ha\u00ADram kıldığının sayısına uydursunlar da, Al\u00ADlah'ın haram kıldığını helal ve meşru kılsın\u00ADlar, isterler. Onların bilinçli kötü amelleri kendilerine süslenip güzel gösterilmiştir. Allah kulluk sözleşmesindeki ortak taahhütlerini, Allah'a iman, kulluk ve sorumlu\u00ADluk bilincini şuur altına iterek örtbas edip, küfürde, nankörlükte ısrar eden bir kavme doğru yolu gösterme lütfunda bulunmaya\u00ADcak, başarı nasip etmeyecektir. (Tevbe, 9/ 36-37).\n" +
                "\n" +
                "Onlar bir yıl, Safer ayını helal sayıyorlar, bir yıl Muharrem'i haram sayıyorlardı. Nesî (yıla ekleme), işte budur. Allah'ım, Sen de şahit ol!. (20)\n" +
                "\n" +
                "Ey İnsanlar! Kadınlarınızın sizler üze\u00ADrinde hakları, sizin kadınlarınız üzerinde haklarınız vardır. Sizin onlardaki hakkınız, minderinize sizden başkasını oturtmama\u00ADları, meşru tavsiyelerinizde size karşı çık\u00ADmamaları, hoşlanmadığınız kişileri izniniz olmadan eve sokmamaları, kötü söz söyle\u00ADmemeleri kötü fiil ve davranışta bulunma\u00ADmalarıdır. Şayet bunları yaparlarsa, Allah onları engellemenize, sıkıştırmanıza yatak\u00ADlarında tek başlarına bırakmanıza ve hafif\u00ADçe, incitmeden vurmanıza izin vermiştir. Bun\u00ADlardan vazgeçer ve size itaat ederlerse, meşru, örfe uygun ölçüler içerisinde rızıklarını ve giyimlerini sağlama sorumluluğunuz var. Kadınların iyiliğini isteyin, durum\u00ADlarının iyileşmesi için çaba sarfedin. Çünkü onlar müşterek hayatın gereği kendileri adına bir şey yapma gücüne ve imkanına sahip olmayan, sizinle birlikte yaşamak mecburiyetinde olan hayat arkadaşlarınızdır. Siz onları Allah'ın emaneti olarak aldı\u00ADnız. Allah'ın emri ve hükmüyle onlarla iliş\u00ADkiyi helal edindiniz. Eğer haklarını ararlar, sorumluluklarına riayet ederlerse onlara tavır takınmanıza, cezalandırmaya hakkı\u00ADnız yoktur. Onların serkeşliğinden ve şid\u00ADdete başvurmasından endişe ederseniz, onlara öğüt verin ve yataklarınızı ayırın. Aşırı gitmeden hafifçe vurun. Onların yi\u00ADyeceği ve giyimi konusunda cömertçe her türlü iyilik ve ihsanda bulunmanız, onların haklarıdır. Kadınların haklarına riayet ko\u00ADnusunda Allah'ın emirlerine yapışın, aza\u00ADbından korunun, onların iyiliğini isteyin, durumlarının iyileşmesi için çaba sarfedin. Hanımlarınız, sizlerin izni ve bilgisi olmadıkça, evinizin mali imkanlarını cömertçe harcamasınlar. Sözlerimi iyice anlayarak hatırınızda tutun.\n" +
                "\n" +
                "İyice tebliğ edebildim mi? Allah'ım, Sen de şahit ol! (21)\n" +
                "\n" +
                "Ey İnsanlar! Meşru şekilde sahip oldu\u00ADğunuz, üzerlerinde meşru haklarınız ve düzgün insani ilişkileriniz olan köle ve ca\u00ADriyelerinize, iş akdiyle bağlı işçilerinize ha\u00ADyırla muameleyi size tavsiye ederim. Sof\u00ADranızda bulunanları ölçü alarak onların ka\u00ADrınlarını doyurmanızı, giydiklerinizi ölçü alarak onların giyimlerini sağlamanızı tav\u00ADsiye ederim. Affetmeyi düşünmediğiniz bir suç işledikleri takdirde aranızda aynı cins\u00ADten suç işleyenlere uyguladığınız cezaları ölçü alınız. Onlara işkence etmeyiniz, onları cezalandırmayınız.!. (22)\n" +
                "\n" +
                "Ey İnsanlar! Sözlerimi iyi dinleyin, iyi muhakeme edin. Bütün ırklara mensup Müslümanların, Müslümanların kardeşi ol\u00ADduğunu bilin. Bütün müminler kardeştir. Kimseye, gönül rızası olmadıkça, kardeşi\u00ADnin malı helal değildir. Sakın haksızlık etmesin, hile yapmasın, haince davranma\u00ADsın.\n" +
                "\n" +
                "Müslümanın kim olduğunu size anlata\u00ADyım mı? Müslümanların, dilinden ve elin\u00ADden zarar görmediği kişidir.\n" +
                "\n" +
                "Müminin kim olduğunu size anlatayım mı? İnsanların mallarına ve canlarına za\u00ADrarı dokunmuyacağından emin olduğu ki\u00ADşidir.\n" +
                "\n" +
                "Muhacirin kim olduğunu size anlatayım mı? Kötülükleri ve günah işlemeyi terk eden kişidir.\n" +
                "\n" +
                "Mücahidin kim olduğunu size söyleye\u00ADyim mi? Allah'a itaat yolunda nefsiyle mücadele eden kişidir.\n" +
                "\n" +
                "Bu günün dokunulmazlığı gibi, müminin mümine zarar vermesi haramdır. Etini yeme mesabesinde olan müminin mümini gıybeti de haramdır. Namus ve haysiyetine zarar vermesi de haramdır. Müminin yü\u00ADzüne tokat vurmak da mümine haramdır. Onu itip kakarak incitmesi de haramdır.\n" +
                "\n" +
                "İyice tebliğ edebildim mi? Allah'ım, Sen şahit ol! (23)\n" +
                "\n" +
                "Ey İnsanlar! Yeryüzü Allah ve Rasûlüne aittir. İnsanlar, 'Allah'tan başka ilah yok\u00ADtur' deyip, benim Allah'ın Rasûlü olduğu\u00ADmu kabul edinceye kadar, insanlarla mücadele etmem, savaşmam emredildi. İn\u00ADsanlar kelime-i tevhidi söyleyince, kan\u00ADlarını, canlarını ve mallarını korumuş olur\u00ADlar. Ancak İslam'ın koyduğu sorumluluk gereği uygulanan gerekçeli karara dayalı cezalar müstesnadır. Ahiretteki hesapları ise Allah'a aittir. Kendinize, birbirinize haksızlık etmeyin!(24)\n" +
                "\n" +
                "Ey Müminler, benden sonra küfre dön\u00ADmeyin, birbirinin boynunu vuran kafirler haline gelmeyin. Size, sımsıkı sarıldığınız sürece asla hak yoldan uzaklaşmayacağınız apaçık dinî, ilmî, idari, siyasi kuralları içe\u00ADren Allah'ın kitabı Kur'ân'ı ve Rasûlü'nün sünnetini bıraktım. Bunlarla amel ediniz, davranışlarınıza Kur'ân ve sünneti yan\u00ADsıtınız. Bir de soyumdan yakınlarımı, Ehl-i beytimi bıraktım.\n" +
                "\n" +
                "İyice tebliğ edebildim mi? Allah'ım, Sen şahit ol! (25)\n" +
                "\n" +
                "Ey insanlar! Rabbiniz birdir, babanız bir\u00ADdir. İslam'da insanlar eşittir. Hepiniz Adem'in çocuklarısınız, Adem de toprak\u00ADtan yaratıldı. Allah katında en değerliniz, en çok Allah'a sığınanız, emirlerine yapışa\u00ADnınız, günahlardan arınanınız, azabından korunanızdır. Bir Arab'ın, Arap olmaya\u00ADna, bir başkasının Arab'a, bir siyahın bir kızılderiliye, bir kızılderilinin bir siyaha, takvanın dışında bir üstünlük sebebi yok\u00ADtur.\n" +
                "\n" +
                "\"Ey iman edenler, biz sizi bir erkekle bir kadından, bir asıldan yarattık. Birbirinizle tanışmanız, işlerinizi tedbirle idare etme\u00ADniz, karşılıklı olarak, İslami kurallarla örtüşen milletlerarası teamüllere uymanız, yardımlaşmanız, kültür ve medeniyet alış\u00ADverişinde bulunmanız, birbirinize iyiliği tav\u00ADsiye etmeniz için, sizi milletler ve kabileler haline getirdik. Allah yanında en değerli\u00ADniz, en üstününüz, en çok Allah'a sığınanı\u00ADnız, emirlerine yapışanınız, en çok günah\u00ADlardan arınıp azaptan korunanız, kulluk ve sorumluluk şuuruyla, haklarına ve özgür\u00ADlüklerine sahip çıkarak şahsiyetli davrana\u00ADnınız, dinî ve sosyal görevlerinin bilincinde olanınızdır. Allah her şeyi bilir, gizli-açık her şeyden haberdardır.\" (Hucurat, 49/13.) (26)\n" +
                "\n" +
                "Ey İnsanlar! Görünürdeki organları kesil\u00ADmiş bir Habeşli bile başınıza getirilse, size Allah'ın kitabındaki hükümleri uyguladığı sürece, dinleyin ve itaat edin.\n" +
                "\n" +
                "İyice tebliğ edebildim mi? Allah'ım, Sen de şahit ol! (27)\n" +
                "\n" +
                "(İnsanlar, ‘evet' dediler)\n" +
                "\n" +
                "Burada bulunanlar, sözlerimi bulun\u00ADmayanlara iletsinler.\n" +
                "\n" +
                "Ey İnsanlar! İyi dinleyin! Bütün peygam\u00ADberlerin daveti geçmişte kalmış, görevleri sona ermiştir. Yalnızca benim davetim ve görevim devam etmektedir. Ben insanların ihtiyacı sebebiyle Rabbimin katında davetimi, görevimi kıyamet gününe kadar muhafaza ettim. Ben önceki ümmetlere karşı sizin çokluğunuzla övüneceğim. Beni mahcup etmeyin, yüzümü kara çıkarma\u00ADyın. (28)\n" +
                "\n" +
                "İyi dinleyin, bir kısım insanlar için elim\u00ADden bir şey gelmezken bir kısmını kurtaracağım. Ya Rabbi ashabım, diyeceğim. Ba\u00ADna, ‘Senden sonra din adına neler icat et\u00ADtiklerini bilmiyorsun', buyuracak. Ben cen\u00ADnetteki havuz başında sizi bekleyen öncünüzüm. (29)   \n" +
                "\n" +
                "Ey İnsanlar! Allah, her hak sahibinin hakkını, her varisin, mirastaki payını belirlemiştir. Varise vasiyet yapılamaz. Vasiyet terekenin üçte birini de geçemez. Çocuk meşru eşe aittir. Zina edenin hak sahipliği söz konusu değildir. Hamisinin, amirinin, ortağının, işvereninin, efendisinin sağladı\u00ADğı imkanlara nankörce davranan, Allah'ın Muhammed'e indirdiği Kur'ân'ı inkar edi\u00ADyor demektir. Babasından başkasına men\u00ADsubiyet öne süren veya efendisinden başkasını veli edinen, Allah'ın, meleklerin ve bütün insanların lanetine uğrasın. Böylesinin ne azabı geri çevrilir, ne ceza yeri\u00ADne fidye alınır. (30)\n" +
                "\n" +
                "Ey İnsanlar! Dinde aşırılıktan sakının. Sizden öncekileri kesinlikle dinde aşırılık\u00ADları helak etmiştir. Hacdaki amelleri, dav\u00ADranışları benden öğrenin. Bu seneden sonra bir daha haccedip edemeyeceğimi bile\u00ADmiyorum. Bu öğütlerimi burada bulunan\u00ADlar bulunmayanlara ulaştırsın. Öğütlerimin ulaştırıldığı bazı kimseler burada dinleyenlerden daha iyi anlayarak, daha iyi mu\u00ADhafaza edebilirler, nice kimseler uygulaya\u00ADrak daha mutlu olabilirler. (31)   \n" +
                "\n" +
                "Ey İnsanlar! Allah sözlerimi işitip de bel\u00ADleyene, rahmetini merhametini ihsan et\u00ADsin. Allah yüzünü ağartsın. Mana yüklü sözlerimi anlamadan ezberleyen birçok in\u00ADsan var. Derin manalar içeren sözlerimi bilen birçok insan, kendisinden daha yük\u00ADsek anlayış sahiplerine bu sözlerimi ulaş\u00ADtırsın. Üç vasfa, üç davranışa sahip olan;\n" +
                "\n" +
                "-Samimiyetle Allah rızası için dinî görev\u00ADlerini yerine getiren,\n" +
                "\n" +
                "-Müslüman idarecilere samimi davranan ve itaat eden,\n" +
                "\n" +
                "-İslam toplumunun birliğini ve bütünlüğü\u00ADnü koruyan müminlerin İslam'a hıyanet etmeyeceğini, kalplerinden İslam'ı atma\u00ADyacağını bilin.\n" +
                "\n" +
                "Bütün müminler gelecek nesilleri, İslam ile şereflenmemiş insanları İslam'a davet ederek İslam'ı tebliğ ve davet görevini yeri\u00ADne getirmelidirler. (32)\n" +
                "\n" +
                "Benim dışımda benden sonra peygam\u00ADber görevlendirilmeyecektir. Sizin dışınız\u00ADda ümmet de olmayacaktır. Rabbinizi ilah tanıyın, candan Müslümanlar olarak Rabbinize teslim olun, saygıyla Rabbinize kulluk ve ibadet edin. Rabbinizin şeriatine boyun eğin, adabına, erkanına riayet ederek beş vakit namazı aksatmadan aşikare kılın. Vicdanı, serveti, sosyal bünyeyi arındıran, berekete vesile olan zekatı verin. Ramazan orucunu tutun. Yöneticilerinize itaat edin ki Rabbinizin cennetine girersiniz. (33)   \n" +
                "\n" +
                "Ey İnsanlar! Yarın Beni size soracaklar. Ne dersiniz? Peygamberlik görevimi yeri\u00ADne getirdim mi? Vazifemi yaptım mı?\n" +
                "\n" +
                "(Orada bulunanlar, ‘evet yemin ederiz ki, tebliğ ettin, bize tavsiyelerde ve öğütlerde bulundun, böylece şehadet ederiz' dediler).\n" +
                "\n" +
                "-Şahit ol ya Rabbi, şahit ol ya Rabbi, şahit ol ya Rabbi...\n" +
                "\n" +
                "Size selam ve selamet diliyorum, Al\u00ADlah'ın rahmet ve bereket ihsanını niyaz ediyorum. (34)   \n" +
                "\n" +
                "(Sonra insanlara veda etti. Bunun üzerine insan\u00ADlar, ‘bu veda haccı' dediler).");
        vedaHutbesiText.setMovementMethod(new ScrollingMovementMethod());





    }


    private void createBannerAd() {


        adView = new MaxAdView( "b06d01f284423f8f", this );


        // Stretch to the width of the screen for banners to be fully functional
        int width = ViewGroup.LayoutParams.MATCH_PARENT;

        // Banner height on phones and tablets is 50 and 90, respectively
        int heightPx = getResources().getDimensionPixelSize( R.dimen.banner_height );

        adView.setLayoutParams( new FrameLayout.LayoutParams( width, heightPx ) );

        // Set background or background color for banners to be fully functional


        ViewGroup rootView = binding.maxAdView;
        rootView.addView( adView );

        // Load the ad
        adView.loadAd();



    }

}