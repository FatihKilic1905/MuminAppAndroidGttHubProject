package com.fatihkilic.muminappandroid.Kutuphane;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;

import com.fatihkilic.muminappandroid.R;
import com.fatihkilic.muminappandroid.databinding.ActivityEzanDuasiBinding;
import com.fatihkilic.muminappandroid.databinding.ActivityHtmDuasiBinding;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class HtmDuasi extends AppCompatActivity {

    private ActivityHtmDuasiBinding binding;
    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_htm_duasi);

        binding = ActivityHtmDuasiBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getSupportActionBar().setTitle("Hatim Duası");

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        mAdView = binding.adView;
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        arapcaHatimDuasi();


        Button ArapcaButton = binding.arapcaButton;
        ArapcaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                arapcaHatimDuasi();
            }
        });

        Button turkceButton = binding.turkceButton;
        turkceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                turkceHatimDuasi();
            }
        });

        Button mealButton = binding.mealButton;
        mealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mealHatimDuasi();
            }
        });






    }


    public void arapcaHatimDuasi () {


        String arapcaHatimStr = "أَلْحَمْدُ لِلَّٰهِ رَبِّ الْعَالَم۪ينَ ۞ وَالْعَاقِـبَـةُ لِلْمُـتَّـق۪ينَ ۞ وَلَا عُدْوَانَ إِلَّا عَلَي الظَّالِم۪ينَ ۞ وَالصَّلٰاةُ وَالسَّلٰامُ عَلٰى رَسُولِـنَا مُحَمَّدٍ وَأٰلِه۪ وَصَحْبِه۪ٓ أَجْمَع۪ينَ ۞ أَللَّٰـهُمَّ رَبَّـنَا يَا رَبَّـنَا تَـقَـبَّـلْ مِنَّا إِنَّكَ أَنْتَ السَّم۪يعُ الْعَل۪يمُ ۞ وَتُبْ عَلَيْنَا يَا مَوْلٰــنَآ إِنَّكَ أَنْتَ التَّــوَّابُ الرَّح۪يمُ ۞ وَاهْدِنَا وَوَفِّقْـنَآ إِلَى الْحَقِّ وَإِلٰى طَر۪يقٍ مُسْتَـق۪يمٍ ۞ بِـبَـرَكَـةِ الْقُرْأٰنِ الْعَظ۪يمِ ۞ وَبِحُرْمَـةِ مَنْ أَرْسَلْـتَـهُ رَحْمَةً لِلْعَالَم۪ينَ ۞ وَاعْفُ عَـنَّا يَا كَر۪يمُ ۞ وَاعْفُ عَـنَّا يَا رَح۪يمُ ۞ وَاغْفِرْ لَـنَا ذُنُـوبَـنَا بِفَضْلِكَ وَجُودِكَ وَكَرَمِكَ يَآأَكْرَمَ الْاَكْرَم۪ينَ ۞ أَللَّٰـهُمَّ زَيِّـنَّا بِز۪يـنَـةِ الْقُرْأٰنِ ۞ وَأَكْرِمْنَا بِكَرَامَةِ الْقُرْأٰنِ ۞ وَشَرِّفْـنَا بِشَرَافَةِ الْقُرْأٰنِ۞ وَأَلْبِسْنَا بِـخِلْعَةِ الْقُرْأٰنِ ۞ وَأَدْخِـلْـنَا الْجَـنَّـةَ بِشَفَاعَةِ الْقُرْأٰنِ ۞ وَعَافِـنَا مِنْ كُلِّ بَلٰٓاءِ الدُّنْـيَا وَعَذَابِ الْاٰخِرَةِ بِحُرْمَةِ الْقُرْأٰنِ ۞ وَارْحَمْ جَم۪يعَ أُمَّـةِ مُحَمَّدٍ يَا رَح۪يمُ يَا رَحْمٰنُ ۞ أَللَّٰـهُمَّ اجْعَلِ الْقُرْأٰنَ لَـنَا فِي الدُّنْـيَا قَر۪ينًا ۞ وَفِي الْقَـبْـرِ مُونِسًا ۞ وَفِي الْقِيَامَـةِ شَف۪يعًا ۞ وَعَلَى الصِّرَاطِ نُـورًا ۞ وَفِي الْجَـنَّـةِ رَف۪يقًا ۞ وَمِنَ النَّارِ سِتْـرًا وَحِجَابًا ۞ وَإِلىَ الْخَـيْـرَاتِ كُـلِّـهَا دَل۪يلًا وَإِمَامًا ۞ بِفَضْلِكَ وَجُودِكَ وَكَرَمِكَ يَآ أَكْرَمَ الْاَكْرَم۪ينَ وَيَآ أَرْحَمَ الرَّاحِم۪ينَ. أَللَّٰـهُمَّ اهْدِنَا بِـهِدَايَـةِ الْقُرْأٰنِ ۞ وَنَـجِّـنَا مِنَ النّ۪ـيرَانِ بِكَرَامَةِ الْقُرْأٰنِ ۞ وَارْفَعْ دَرَجَاتِـنَا بِفَض۪يلَـةِ الْقُرْأٰنِ ﴿﴾ وَكَفِّرْ عَـنَّا سَيِّأٰتِـنَا بِـتِـلٰاوَةِ الْقُرْأٰنِ ۞ يَا ذَا الْفَضْلِ وَالْاِحْسَانِ ۞ أَللَّٰـهُمَّ طَهِّرْ قُـلوُبَـنَا ۞ وَاسْتُرْ عُيوُبَـنَا ۞ وَاشْفِ مَرْضَانَا ۞ وَاقْضِ دُيُـونَـنَا ۞ وَارْفَعْ دَرَجَاتِـنَا ۞ وَارْحَمْ أٰبَآءَنَا ۞ وَاغْفِرْ أُمَّـهَاتِـنَا ۞ وَأَصْلِحْ د۪يـنَـنَا وَدُنْـيَانَا ۞ وَشَتِّتْ شَمْلَ أَعْدَآئِـنَا ۞ وَاحْفَظْ أَهْلَـنَا وَأَمْوَالَـنَا وَبِلَادَنَا مِنْ جِم۪يعِ الْاٰفَاتِ وَالْاَمْرَاضِ وَالْـبَـلٰايَا ۞ وَثَـبِّتْ أَقْدَامَنَا وَانْصُرْنَا عَلَى الْقَوْمِ الْكَافِر۪ينَ ۞ بِحُرْمَةِ الْقُرْأٰنِ الْعَظ۪يمِ ۞\n" +
                "\n" +
                "أَللَّٰـهُمَّ بَـلِّـغْ ثَــوَابَ مَا قَرَأْنَاهُ ، وَنُـورَ مَا تَـلَوْنَاهُ ، إِلٰى رُوحِ سَيِّـدِنَا وَنَـبِـيِّـنَا مُحَمَّدٍ صَلَّى اللّٰهُ تَـعَالٰى عَلَـيْـهِ وَسَلَّمَ ۞ وَإِلٰٓى أَرْوَاحِ جَم۪يعِ الْاَنْبِـيَـآءِ وَالْمُرْسَل۪ينَ ، صَلَوَاتُ اللّٰهِ وَسَلٰامُهُ عَلَـيْهِمْ أَجْمَع۪ينَ ۞ وَإِلٰٓى أَرْوَاحِ أٰلِه۪، وَأَوْلٰادِه۪ ، وَأَزْوَاجِه۪، وَأَصْحَابِـه۪، أَتْـبَاعِه۪، وَجَم۪يعِ ذُرِّيَّاتِـه۪ رِضْوَانُ اللّٰهِ تَعَالٰى عَلَـيْـهِمْ أَجْمَع۪ينَ ۞ وَإِلٰٓى أَرْوَاحِ أٰ بَآئِـنَا، وَأُمَّـهَاتِـنَا، وَإِخْوَانِـنَا وَأَخَوَاتِـنَا، وَأَوْلَادِنَا، وَأَقْرِبَآئِـنَا، وَأَحِبَّآئِـنَا، وَأَصْدِقَآئِـنَا، وَأَسَات۪يذِنَا، وَمَشَايِـخِـنَا، وَلِمَنْ كَانَ لَهُ حَقٌّ عَلَـيْـنَا ۞ وَإِلٰي أَرْوَاحِ جَم۪يعِ الْمُؤْمِن۪ينَ وَالْمُؤْمِنَاتِ، وَالْمُسْلِم۪ينَ وَالْمُسْلِمَاتِ، أَلْاَحْـيَآءِ مِـنْـهُمْ وَالْاَمْوَاتِ ۞ يَا قَاضِيَ الْحَاجَاتِ وَيَا مُج۪يبَ الدَّعَـوَاتِ ۞ رَبَّـنَآ أٰتِـنَا فِي الدُّنْـيَا حَسَنَةً وَفِي الْاٰخِرَةِ حَسَنَةً وَقِنَا عَذَابَ النَّارِ ۞ أَللَّٰـهُمَّ رَبَّـنَا اغْفِرْ ل۪ي وَلِـوَالِدَيَّ وَلِلْمُؤْمِن۪ينَ يَوْمَ يَقُومُ الْحِسَابُ";


        binding.textView.setText(arapcaHatimStr);
        binding.textView.setMovementMethod(new ScrollingMovementMethod());

    }

    public void turkceHatimDuasi () {


        String turkceHatimStr = "“El-hamdü lillâhi Rabbil-‘âlemîn.\n" +
                "\n" +
                "Vel-‘âkibetü lil-müttekîn. Velâ ‘udvâne illâ ‘alezzalimîn.\n" +
                "\n" +
                "Ves-salâtü ves-selâmü ‘alâ Rasûlinâ Muhammedin ve ‘âlihî ve sahbihî ecme'în.\n" +
                "\n" +
                "Rabbenâ takabbel minnâ inneke ente’s-semî’ul-‘alîm.\n" +
                "\n" +
                "Ve tüb ‘aleynâ yâ Mevlânâ inneke ente’t-tevvâbür-Rahîm.\n" +
                "\n" +
                "Vehdinâ ve veffiknâ ilel-hakkı ve ilâ tarîkın müstekîm. Bi beraketil-Kur’ânil-‘azîm.\n" +
                "\n" +
                "Ve bi hürmeti men erseltehû rameten lil-‘âlemîn.\n" +
                "\n" +
                "Va’fü ‘annâ yâ Kerîm. Va’fü ‘annâ yâ Rahîm.\n" +
                "\n" +
                "Vağfir lenâ zünûbenâ bi fadlike ve keramike yâ ekramel-ekramîn.\n" +
                "\n" +
                "Allâhümme zeyyinnâ bi zînetil-Kur’ân.\n" +
                "\n" +
                "Ve ekrimnâ bi kerâmetil-Kur’ân.\n" +
                "\n" +
                "Ve şerrifnâ bi şerâfetil-Kur’ân.\n" +
                "\n" +
                "Ve elbisnâ bi hil’atil-Kur’ân.\n" +
                "\n" +
                "Ve edhilnel-cennete bi şefâatil-Kur’ân.\n" +
                "\n" +
                "Ve ‘âfinâ min külli belâid-dünyâ ve ‘azâbil-âhirati bi hurmetil-Kur’ân.\n" +
                "\n" +
                "Verham cemî’a ümmet-i Muhammedin yâ Rahîmü yâ Rahmân.\n" +
                "\n" +
                "Allâhümec’alil-Kur’âne lenâ fid-dünyâ karînâ.\n" +
                "\n" +
                "Ve fil-kabri mûnisâ.\n" +
                "\n" +
                "Ve fil-kıyâmeti şefî’ân ve ‘ales-sırâti nûrâ.\n" +
                "\n" +
                "Ve ilel-cenneti rafîkâ.\n" +
                "\n" +
                "Ve minennâri sitran ve hicâbâ.\n" +
                "\n" +
                "Ve ilel-hayrâti küllihâ delîlen ve imâmâ. Bi fadlike ve cûdike ve keramike yâ Kerîm.\n" +
                "\n" +
                "Allâhümmeh-dinâ bi hidâyetil-Kur’ân.\n" +
                "\n" +
                "Ve neccinâ minen-nîrâni bi kerâmetil-Kur’ân.\n" +
                "\n" +
                "Verfa’ deracâtina bi fadîletil-Kur’ân.\n" +
                "\n" +
                "Ve keffir ‘annâ seyyiâtinâ bi tilâvetil-Kur’ân. Yâ zel-fadli vel-ihsân.\n" +
                "\n" +
                "Allâhümme tahhir kulûbenâ.\n" +
                "\n" +
                "Vestur ‘uyûbenâ.\n" +
                "\n" +
                "Veşfi merdânâ.\n" +
                "\n" +
                "Vekdi duyûnenâ.\n" +
                "\n" +
                "Ve beyyid vücûhenâ.\n" +
                "\n" +
                "Verfa’ deracâtina.\n" +
                "\n" +
                "Verham âbâenâ.\n" +
                "\n" +
                "Veğfir ümmehâtinâ.\n" +
                "\n" +
                "Ve eslih dînenâ ve dünyânâ.\n" +
                "\n" +
                "Ve şeddid şemle a’dâina.\n" +
                "\n" +
                "Vehfaz ehlenâ ve emvâlenâ ve bilâdenâ min cemî’l-âfâti ve’l-emrâdi ve’l-belâyâ.\n" +
                "\n" +
                "Ve sebbit akdâmenâ, ven-surnâ ‘alel-kavmil-kâfirîn. Bi hurmetil-Kur’ânil-‘azîm.\n" +
                "\n" +
                "Allâhümme belliğ sevâbe mâ kara’nâhü.\n" +
                "\n" +
                "Ve nevvir mâ televnâhü ilâ rûhi seyyidinâ Muhammedin sallâllahü te’âlâ ‘aleyhi ve selem.\n" +
                "\n" +
                "Ve ilâ ervâhi cemî’ı ihvânihî minel-enbiyâi vel-murselîn. Salevâtullâhi ve selâmühû ‘aleyhim ecma’în.\n" +
                "\n" +
                "Ve ilâ ervâhi âlihî ve evlâdihî ve ezvâcihî ve ashâbihî ve etbâ’ıhî ve cemîı’ zürriyyâtihî rıdvânullâhi te’âlâ ‘aleyhim ecma’în.\n" +
                "\n" +
                "Ve ilâ ervâhi âbâinâ ve ümmehâtinâ ve ihvâninâ ve ehavâtinâ ve evlâdina ve akribâinâ ve ehibbâinâ ve asdikâinâ ve esâtîzinâ ve limen kâne lehû hakkun ‘aleynâ ve li cemî’ıl-mü’minîne vel-mü’minâti vel-müslimîne vel-müslimâti, el-ahyâi minhüm vel-emvâti.\n" +
                "\n" +
                "Yâ kâdiyel-hâcâti! Yâ mücîbed-d’avâti! İstecib du’âenâ bi rahmetike yâ erhamer-râhimîn. Sübhâne Rabbike Rabbil-‘ızzeti ‘ammâ yasıfûn. Ve selâmün ‘alel-mürselîn. Vel-hamdü lillâhi Rabbil-‘âlemîn. el-Fatiha";

                binding.textView.setText(turkceHatimStr);
                binding.textView.setMovementMethod(new ScrollingMovementMethod());

    }

    public void mealHatimDuasi () {


        String mealHatimStr = "Âlemlerin Rabbi olan Allah’a hamd olsun. İyi sonuç müttakilerindir. Düşmanlık ancak zalimler içindir. Peygamberimiz\n" +
                "\n" +
                "Hz. Muhammed (a.s)’e, onun bütün ehl-i beytine ve ashâbına salât ve selâm olsun.\n" +
                "\n" +
                "Ey Rabbimiz! Bizden ibadetlerimizi kabul buyur! Şüphesiz ki sen her şeyi işiten ve her şeyi bilensin.\n" +
                "\n" +
                "Ey Mevlamız! Bizim tövbelerimizi kabul eyle!. Şüphesiz ki sen tövbeleri çok çok kabul eden ve merhametli olansın. Bize hidâyet ver! Hak yola ve sırat-ı müstakime ulaşmayı bizi muvaffak eyle!. Yüce Kur’ân’ın hürmetine, âlemlere rahmet olarak gönderdiğin Peygamber hürmetine.\n" +
                "\n" +
                "Ey Kerim olan Allah! Bizi bağışla. Ey Rahim olan Allah! Bizi bağışla. Ey ikram edenlerin en keremlisi olan Allah! Lütfunla ve ihsanınla bizim günahlarımızı bağışla.\n" +
                "\n" +
                "Allah’ım! Bizi Kur’ân süsü ile süsle. Kur’ân ile bize lütfet! Kur’ân ile bizi şereflendir. Kur’ân elbisesini bize giydir. Kur’ân hürmetine bizi cennetine koy. Kur’ân hürmetine dünyadaki belalardan ve âhiret azabından bizi koru. Ey Rahim, Ey Rahman! Ümmet-i Muhammed’in tamamına merhamet et.\n" +
                "\n" +
                "Allah’ım! Kur’ân’ı bize dünyada yoldaş eyle. O’nu bize kabirde dost eyle. Kıyamet günü onu bize şefaatçi kıl, sırat köprüsü üzerinde onu bize nur eyle. Cennette onu bize yoldaş eyle. Cehennem ateşine karşı onu bize perde ve engel kıl. İhsanın, cömertliğin ve keremin ile tüm hayırlı yollar için onu bize önder kıl.\n" +
                "\n" +
                "Kur’ân hidâyeti ile bizi hidâyete eriştir. Kur’ân’ın hürmetine bizi ateşten koru. Kur’ân hürmetine bizim derecemizi yükselt. Okunan Kur’ân hürmetine günahlarımızı bağışla. Ey Lütuf ve ihsan sahibi!.\n" +
                "\n" +
                "Allah’ım! Kalplerimizi temizle. Kusurlarımızı ört. Hastalarımıza şifa ver. Borçlarımızı ödemeye yardım et. Yüzümüzü aydınlat. Derecemizi yükselt. Babalarımıza merhamet et. Annelerimizi bağışla. Din ve dünya işlerimizi islâh et. Düşmanlarımızın bize saldırısını bertaraf eyle. Ailemizi, mallarımızı, memleketimizi her türlü afetlerden, hastalıklardan ve belalardan koru. Ayaklarımızı sabit eyle, kâfir toplumlara karşı bize yardım et. Yüce Kur’ân hürmetine.\n" +
                "\n" +
                "Allah’ım! Okuduğumuz ve tilavet ettiğimiz Kur’ân’ın sevabını ve nurunu Efendimiz Hz. Muhammed (a.s)’in ruhuna ulaştır. Ve onun kardeşleri olan tüm peygamberlerin (a.s) ruhlarına ulaştır. Ve Peygamberimiz (a.s)’in ehlinin, çocuklarının, hanımlarının, ashabının, tabiinin ve bütün zürriyetinin ruhlarına ulaştır.\n" +
                "\n" +
                "Hayatta olan veya vefat etmiş olan babalarımızın, annelerimizin, kardeşlerimizin, evladımızın, akrabalarımızın, sevdiklerimizin, dostlarımızın, hocalarımızın, üzerimizde hakkı olan herkesin ve Müslüman olan bütün kadın ve erkeğin ruhlarına ulaştır.\n" +
                "\n" +
                "Ey ihtiyaçları gideren Allah! Ey dualara icabet eden Allah! Ey merhametlilerin en merhametlisi! Dualarımızı kabul et. Tüm peygamberlere salât ve selam olsun.\n" +
                "\n" +
                "Senin Rabbin; kudret ve şeref sahibi olan Rab, onların nitelendirdiği şeylerden uzaktır, yücedir. Peygamberlere selam olsun. alemlerin Rabbi olan Allah’a hamdolsun.\n" +
                "\n" +
                "Fatiha denir ve Kur’an’ın birinci suresi (Fatiha) okunur.";

                binding.textView.setText(mealHatimStr);
                 binding.textView.setMovementMethod(new ScrollingMovementMethod());


    }


}