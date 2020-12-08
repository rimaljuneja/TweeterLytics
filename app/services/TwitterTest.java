package services;

import static java.util.concurrent.CompletableFuture.supplyAsync;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CompletionStage;
import models.Tweet;

/**
 * Twitter API Implementation for Testing
 * 
 * @author Azim Surani
 * @version 1.0.0
 */
public class TwitterTest implements TwitterApi {

	Map<String, List<Tweet>> mockTweets = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
	

	public TwitterTest() {

		// Happy Tweets for keyword I am Happy
		mockTweets.put("I am Happy", new ArrayList<>(Arrays.asList(new Tweet(
				"I am happy that audience is appreciating good content on OTT and are looking beyond ?hot? scenes ? @jaysoni25Follow Us on @iwmbuzz @krishnavbha tt@TheRealPriya#JaySoni #KrishnaBhatt #mustread #PriyaBanerjee #Twisted3https://t.co/l6J10HjRJ4",
				"iwmbuzz"),
				new Tweet("@James33172570 Hey sister James, it doesn't matter, I am happy for you got a shiny ??????",
						"shinybaobao"),
				new Tweet(
						"a follow has been sent to you, eonni ? anyway i am a flover and i am happy when you're comeback hehehe https://t.co/fuY30ph0f4",
						"kwcnjian"),
				new Tweet(
						"I am happy!#affirmations #leadership #leadershipdevelopment #management #managementdevelopment #leadershipcoaching #managementcoaching #balance dlife #worklifebalance #lifecoaching https://t.co/6rBtlLa5T4",
						"CoachingforInsp"),
				new Tweet(
						"Thogh I don't watch his channel because of too much level of noise pollution over there yet I am happy to see the SC granting him the bail.#IDo ntSupportRepublic",
						"GaneshJsmith"),
				new Tweet(
						"On this Veterans Day 2020, I am happy to announce the availability of my new book!Conducting performance-based Instructional Analysishttps://t. co/NvgcqUBZbk https://t.co/YDoA4vkNtl",
						"guywwallace"),
				new Tweet(
						"@OfficialMonstaX .My life we ??love you I want to give you happiness every day, you are the most beautiful person in my heart and I am happy wh en you are happy #LoveKilla2ndWin #MONSTAX@OfficialMonstaX",
						"Andyymonbebe"),
				new Tweet(
						"@nessahatake21 I AM SO GLAD YOU LIKE HIM SJDJSJ I am happy when other people love Renji too!! ????????? I?ve been a Renji fan (and simp) for ye ars already ????",
						"AliceLaiho"),
				new Tweet(
						"The only person I compete against is my past self. Have I managed better than yesterday. If the answer is yes I am happy.",
						"RekaSundaram"),
				new Tweet(
						"Democrats couldn't figure out how on earth they could unseat Donald Trump, so they settle for British product (Rigging) sold to Nigeria. But wi th this one rubbish act their journey to White House has been made more difficult. Sorry!!I am happy for their are still good people.",
						"theophiletra"),
				new Tweet("@yumekosh ok am i supposed to be hurt or sum,,, i am happy for you??? ?", "cherrykawa"),
				new Tweet("@DoMe3908 I am happy you feel that way???", "jousuke_SandW"),
				new Tweet(
						"I am happy. You can?t spoil my mood. I am happy. I?m about be fed as a stunt brand shipper that I am. I am happy. You can?t spoil my mood. http s://t.co/pKcdt3wKgu",
						"Nezr1"),
				new Tweet(
						"@SilenceInPolish Wspania?y Genera?, strateg, Cz?owiek.I am happy to learn that another monument will be erected in March in Breda. Wonderful D utchmen !",
						"halina_moulin"),
				new Tweet(
						"@Domidest @norwegian76 @seanhannity I never thought I would say this, but right now I am happy that I live in South Africa and not the US!",
						"Wi ldFigg"),
				new Tweet("I am blessedI cannot be disadvantagedI am happyI am lovedI am thankfulI am grateful",
						"Oluwatosinoj"),
				new Tweet("@weekend3warrior Wow. Not sure if I am happy or fearful to see that?", "mja2231"),
				new Tweet(
						"@DietHeartNews @LDLSkeptic McDonal's is a human cell degrading machine. Manipulated edible solutions to excite your taste buds and reward brai n cells so that you don't only eat one nugget, you eat the whole box.Haven't stepped in one in years. I am happy to eat natural and clean.",
						"isaacsakko"),
				new Tweet(
						"Can we have a group hug warriors..I am happy We are with u sirLet us fight together for SSR to get justice #ArnabIsBack",
						"HopeofSSR"),
				new Tweet("I am Happy?No Words?#ArnabGoswami #arnabisback https://t.co/kOzhllAqbg", "SriramKannan77"),
				new Tweet(
						"@UnSubtleDesi I am happy for Arnab today but my heart is still bleeding for state sponsored terrorism on #sameetthakkar#Anuragpoddar#PalgharSad huLynching",
						"BawaliBaba2"),
				new Tweet("@amu_osu I am happy", "osuAtipir"),
				new Tweet(
						"@dwnews That is good news. On a side note, some friends ask me if I'm running @dwnews Twitter handle for there are occasional/frequent typos in their tweets. I am happy to share the blame ;-)",
						"NafeesRehmanDr"),
				new Tweet(
						"I got to finish this for today's ObeyMember! I am happy that Beel's turn is on 11.11 which is Pocky Day in Japan. ? It's Emmsy (Obey Me OC) and Beelzebub?#OBEYMEmber #obeymebeelzebub #obeyme https://t.co/hDxThCHwkB",
						"089Kiiru"),
				new Tweet(
						"@vinxi67 @AlexTaylorNews I am happy as long as I live in a democracy . Why are you still here?",
						"Globan999"),
				new Tweet(
						"@republic #ArnabisbackI am happy that my brother Arnab Goswami is back https://t.co/kQHDZalxAz",
						"BinduTi26049337"),
				new Tweet("@I__am_happy_ ????", "shiina_OBR"),
				new Tweet("@I__am_happy_ ????????????????????????????????????", "mi_dd_le"),
				new Tweet("@safanator @InternetFF I am happy that there's a sun shining in your mind ??", "WafHeikal"),
				new Tweet(
						"@Dilaikfandom1 @OrmaxMedia @ColorsTV I am happy Viewers Choice Rubina #AbhinavShukla youtube channel #BodhiArts #AbhinavShukla #RubinaDilaik #J asminBhasin #NishantSinghMalkani #SaraGurpal #ShezadDeol #OrmaxCIL #OrmaxMedia #BBTrendMasterRubinaDilaik #BBTrendMasterAbhinavShukla",
						"NayanaSpatil6"),
				new Tweet("good morning, I just overslept for 2 hours and i am happy about it", "_thesunfl4wer_"),
				new Tweet(
						"Good Evening?????I worked late and just got home.?????????????????I am happy to meet my wife and children????????????",
						"dragon_sanfran"),
				new Tweet("I am happy for Ckay", "Eddynonsoo"),
				new Tweet("Yes! I am happy to hear this! https://t.co/Y8bHwPWpgH", "Sruss745"),
				new Tweet(
						"One day - all will be revealed. I am happy to wait. If corruption is proved - it just means this gang of cricks will have their prison sentence delayed. https://t.co/z7UAtiMnRi",
						"ArakapasHash"),
				new Tweet(
						"I am happy with the Justice pronounced by SC. Celebrations for @republic as it is family to us now. We continue our fight for SACH. @TheKavitaS ingh @pradip103 @anujakapurindia @KanganaTeam ?????? ??? ????? ?? ????, ????? ???? ??????? ?????? ????? ?? ???? ??? ?? ?????",
						"AbhijeetShwas"),
				new Tweet("I have acquired diona and childe,,, I am happy", "BunnPrince"),
				new Tweet(
						"@LastQuake @Erthquake @QuakesToday Of course I am happy if I will share with you and everyone interested in it.",
						"GjuziOlgert"),
				new Tweet("@Chums_oliveth I am happy about this ?", "Bryantt____"),
				new Tweet(
						"@LyndseyNoel5 @ReamsElemAP @jodi_seitz @BurwellBernard I am happy to support all of the hard-working, student-centered teachers at Reams! Such a great school!!??",
						"CCPSEducator"),
				new Tweet(
						"@TheDemCoalition I believe in Democracy, the value of the law, and science, which is why I am happy that I voted for #PresidentBidenVPHarris202 0 #BidenWonDay",
						"KathleenLibby8"),
				new Tweet(
						"@pradip103 I am happy to that #ArnabGoswami has released... Thank you Honourable Supreme Court #SupremeCourtofIndia, Thank you @Swamy39",
						"kanha 0909"),
				new Tweet(
						"I am happy to see my family enjoying this night ?While I am here solo kumaen binabagyo pa tas nakiki away pa sa mga pets ko hahahahahahaAng hir ap lang. Sorry if I seek hugs, circles, care, & attention. Mapapa sana all na lang ako. *cry ko na lang deep explanations ko* https://t.co/Ig0Gy0tofn",
						"As heletAquino"),
				new Tweet(
						"I am happy to see that my recent @OrientXXI article has gained some attention from local media. Here it is featured in a Hargeysa-based media o utlet: https://t.co/HQED3YYqBE",
						"IvanUlisesKK"),
				new Tweet(
						"The time has come. I am happy to announce the launch of my website.Please visit https://t.co/EtQHEwr2c9 untuk mengetahui informasi eksklusif da n semua hal tentang kehidupan saya di luar lapangan sepak bola.Terima kasih. ??#MKTheLabel https://t.co/F2hpOgTrsc",
						"marcklok10"),
				new Tweet("@PMewwww I am happy for you!!! Yay!", "msgkttts"),
				new Tweet("I am happy, I am healthy, I am grateful ??", "TimotheaParris"),
				new Tweet(
						"@ishkarnBHANDARI Sir we can see ur happiness also our thats why u write smelling \"baik\" i also wrote \"arnabgoswia\"but i deleted that sir et ni khusi main kya likhungi......sir its not comment to u sir its real win of our legend arnab sir...i am happy also cry....god bless all...thanks sir...",
						"SaritaB18116169"),
				new Tweet("I am happy she lived to see the day #KamalaHarrisVP https://t.co/jxYCyo64fR",
						"scarsdaletalent"),
				new Tweet(
						"@republic I am 76 years old. Last 8 days it has been rest less days.Today I am happy about Arnab bail. Today is Diwali for me. Continue your Fe arless journalism. Long live Arnab",
						"Ragram611"),
				new Tweet(
						"In the last seven days, I've met three people diagnosed with MS. And I am confused. ?I am happy because they contacted and asked me about the l ocal MS community but I'm not sure should I be happy, because the number of people with MS increased in the last couple of years. ????",
						"PPuljic92"),
				new Tweet(
						"@BBKittyF2 Wow, that is a long MRI, I am happy that you were able to get this done. Good luck Kitty.",
						"Jeffloves1"),
				new Tweet(
						"@therealsteavis @GavinBrannan @lukebrodin1 @MLedermueller @johnnyddavidson @TOwolfpack You are indeed correct. I am happy to stand in the prese nce of facts and be corrected ?",
						"CJCShadowsan"),
				new Tweet(
						"@narendramodi thanks to you sir take initiative of Arnav Gwoswami I am happy to he is release now And congratulations victory of biharPappu fail ki tarah Lalu ki rail ( beta) bhi aapne fail kar di Great sir",
						"JITENDR57412204"),
				new Tweet(
						"I am happy becouse my bro happy Aapka ye happy face dekhkar bahut achcha lga love u god bless u @pradip103 @Chandan81849631 https://t.co/QSAt1 G0WCf",
						"preetish1407"),
				new Tweet("wala na kaming classes, i am happy. please stay safe filo oomfs.", "trzaces"),
				new Tweet(
						"I am happy #NitishKumar won #BiharElectionResults2020 But with #BiharResults I missed mujra of @sardesairajdeep in newsroom.As per so called \" Sources\" this time Rajdeep got disco lights too. ????????.",
						"hvalecha"),
				new Tweet("OMGG THEY DESERVE THIS BIG DORM I SWEARRRRR AARGHHH I AM HAPPY", "theonlyheeseung"),
				new Tweet("@JoelOsteen Amen and I am happy", "Donnedada2"),
				new Tweet("@pratibhabarfa @aajtak Yeah I am happy and you", "Saurabh50004958"),
				new Tweet(
						"MICHAEL: I am happy and then, you are very different tastes and you ain't gonna be called, but... my point is... my point of view...98 others.",
						"TheMarkovOffice"),
				new Tweet(
						"@NForeverozge @ozgecangurel Thanks Nina ... Just because you congratulated me, I won my day I am happy to receive the affection of my friends?? ???",
						"rtemis36112006"),
				new Tweet(
						"@ComesAnnemarie Anniemarie, thank you for following me on Twitter. I am happy to follow you back on Twitter.",
						"BrittenumFaye"),
				new Tweet(
						"Book Blitz Time ~ Today I am happy to be part of the Blitz for Winston Wiggles by Megan Cline~ Come read an excerpt + more! https://t.co/pJ1rCR gxy4 #bookbloggers #blogging #wombat #childrensbooks #picturebooks #cute #TheClqRT #TRJForBloggers @lovingblogs @cosyblogclub #BBlogRT https://t.co/sifxvm B68o",
						"Mehsi_Hime"),
				new Tweet(
						"@MweshiSandra @normanchips I am happy you acknowledge the quality of leaders we have in the UPND. We are not short of alternatives unlike the # ChipantepanteClubPF . Who are the leaders in PF? I know you are proud of the minister and so called bulldozer Hon. Lusambo and the other 2 pornstars. So t alented",
						"katezi14"),
				new Tweet(
						"hi! because I am happy to get sungjin update today, I want to do small GA ? ?? Griptok The Demon?? Malaysian Myday ???? 1 winner will be chosen ?? Reply #MAMAVote #DAY6?? Postage all by me ?? End : 12 Nov 2020 0900hrs https://t.co/Ukc7pA2eZf",
						"Douxhyunsik"),
				new Tweet("What if I am happy now ! 5:38 am", "jhonpar39740314"),
				new Tweet(
						"Too much of our energy has been drained, can they even understand this? I am happy that I wouldn?t be having any finals except for my ortho osc e at the end of this semester or else i would be losing my mind ?",
						"asl97nb"),
				new Tweet(
						"I was going to buy this in Japanese one day but now I am happy I can get the English edition instead! https://t.co/OQujZfm3dF",
						"boysloveparty"),
				new Tweet(
						"I am happy today supreme Court of India granted bail to arnab ghoswami. Actually we didn't do anything it's all about pushpendra kulshrestha ha rd work that brings arnab goswami out of jail we always with you sir Jay Shri ram",
						"Kuldeep35704663"),
				new Tweet(
						"@ManojSaru @oppomobileindia Diwali is festival of lights ,gift and happiness. This year is also different from other years.Until I am happy. So I wish to be more happy from OPPO and Manoj Bhai. #OPPODiwaliWithTechGyan",
						"Prince56818029"),
				new Tweet("@ThePushpendra_ Very good I am happy", "MamtoraRamesh"),
				new Tweet(
						"when you are on right path nothing can defeat you, and i am happy that we got justice.? #ArnabGoswami",
						"freedomofthou18"),
				new Tweet(
						"I am happy I am not the only one who thought this was a xxl selfie-stick. #journalistinprogress #microphoneholder ? https://t.co/ULgGKv9jza",
						"D 0verkleeft"),
				new Tweet("@Mark_nificent Bit tired :)But i am happy with my two poems today. Thanks dear MarkLater?",
						"Julita56795382"),
				new Tweet(
						"As for the challenge of gender balance, they debunk a myth of it being justified by age structure. As an enthusiast and having myself benefited from early-career positions, such as those enacted by @regstud journals, I am happy to see at least this 'age balance' good news.",
						"pedrov_amaral"),
				new Tweet("@RadioHolly I am happy about it but also, I don?t know if this will work next week lol",
						"erinava"),
				new Tweet("@michaelfinucane I am happy to be corrected.", "DonaldClarke63"),
				new Tweet(
						"@ArnabGofficial7 Satyamew jayte A. Bhai buhame khushi hai ki aap jail se bahar air and I am to distort to this hospital because that tell you a bout but discord in this hospital but I am happy so I am celebrate to Diwali thanks",
						"RanajeetPatel1"),
				new Tweet(
						"@TheTrueTankMan The only reason I want all under heaven to coagulate is there is no more need to ask which king is ruling here. As long as I do n't see a group of hooligans throat singing while stealing my fancy jar and a suspicious tollgate demanding my last peck of rice, I am happy.",
						"baabaaer"),
				new Tweet(
						"?? I?m glad you are happy to call me friend. I am happy to call you friend. https://t.co/ZGieGyglub",
						"Sayaberri"),
				new Tweet(
						"i just found my oldest felix fan arts from an old 2018(?) folder ahh i am happy i've kinda gone with some progress lately huhuuu",
						"nlixette"),
				new Tweet("Jinki is back all Taemin can think of is SHINee comeback I am happy ?", "Nymley_Quinn"),
				new Tweet(
						"@chilolo27 wow. i am so sorry to hear this my love. this is such a scary experience. i am happy that you are safe and have been treated for you r injuries. rest up ???",
						"duchyofleo"),
				new Tweet(
						"@FamousJerry Good morning, Jerry. I am happy to read it all worked out. We appreciate your kind words and we appreciate your loyalty! Jan",
						"Del ta"),
				new Tweet(
						"I am happy @HardwareUnboxed are making optimised settings videos and pointing out these things too. I really want a cultural shift in the PC s pace to move people away from \"set it to ultra and complain\". It was possible to \"set games to ultra\" at the end of the PS360 era... https://t.co/C0zg DVlj0K",
						"Dachsjaeger"),
				new Tweet(
						"@heatherdawnmay @heatherdawnmay AS LONG AS YOU ANSWER ME WHEN I NEED YOU, I AM HAPPY TO HEAR FROM YOU. YOU ARE A REAL FRIEND AND A BLESSING TO ME. ?? U 2 DEATH!",
						"ElliottLadette"),
				new Tweet(
						"@that_shaman Now i am happy to have paused the game 6 months, i had do this sachiev just with relog my charact (Arenanet really uses the poor m an's method to keep players waiting)",
						"K_Ermite"),
				new Tweet(
						"That said, if you have a strong opinion about what Node.js should do regarding cancellation please _do_ email me (email can be found in the Nod e.js repo readme) or send me a facebook message. I am happy to chat I just don't like the violence of twitter.",
						"benjamingr_"),
				new Tweet(
						"Good morning . Welcome to Mandarin Oriental Hotel. My name is Charles, Is there anything I could help? Hi, may I have a morning call at 6:30, and also prepare a taxi for me at 7:00, I need to go to Toronto Pearson airport . I am happy to help you",
						"JackJackgao13"),
				new Tweet(
						"@FakeMikeGorman Mike, I am happy to check your area status. Please DM your complete home address and include the name of the primary account ho lder. We are here 24/7. Thank you, Mike",
						"CoxHelp"),
				new Tweet(
						"@kyliestan8 Yeah to beat Gaza?s first weeks sales. I am happy with her being number 1 and being the first female to have number 1s in 5 decades .",
						"Loz_wb"),
				new Tweet("I am Happy", "i7lb_l"), new Tweet("@DiamonDie Very cool, I am happy to hear", "m_ashcroft"),
				new Tweet(
						"Once again thanku for this lovely song and beautiful video to giving ous ... Literally _ i have no words to say , how happy I am .. Happy with your all works .. you never disappointed ous ... Thats why you are the Best ... Best wishes for you ??? @ijassmanak https://t.co/OG2YIYXx4j",
						"Inishusingh"),
				new Tweet("@ShefVaidya Don?t give them logic just say I am happy for you.. it for sure hurts more ??",
						"FleckPapa"),
				new Tweet(
						"@Dev136118 Really , now I am happy, Arnab Goswami is Back, #IStandWithArnab ?#Nationuited?#jaidhriRam?#jaihindudtan??",
						"VijayaRana10"),
				new Tweet("#ArnabIsBack I am so excited and I am happy ??????????", "AnjaliBhatkari7"),
				new Tweet(
						"@_nym3r1a @Sys_Revolt @ActivistHat_ @VitalAnon @Technotna @Anonymo13761588 @AnonArgMMM2020 @anonargcordoba @YourAnonClovers @YourMarkLubbers @a nonlk5 @Global_hackers @OpChildSafety1 @2709vhf @Y0urAnonOPS @Terrence_STR @Buckaroo_0_2 @VoxPopuliMx @JuanCalosoooooo @filosofus40 @nonymousglobal_ @Chal ecosAmarill @Madmomanon660 @_d_a_n_i_e_l__b @ItsLulu_7 @LodeVanB @WolfGodOfCorn @Anons_revenge @aMINE96444917 @AmbazoniaTigers @RobertR41182121 @Kaleigh23 673557 @Anonwatch47374 @AnonNewsUS I am happy it does.",
						"LokiUnleashed1"),
				new Tweet("@HMOIndia @AmitShah @crpfindia Thanks for you sir I am happy Arnab is back",
						"RajPalY38560294"))));

		// Sad Tweets for keyword I am feeling Lonely
		mockTweets.put("I am feeling Lonely", new ArrayList<>(Arrays.asList(new Tweet(
				"hello i am feeling lonely so friend applications are openn or just ask a random question, no pressure to actually form connections hahahttps:// t.co/9sZLT8M4UW",
				"rxnnyroo"),
				new Tweet(
						"Videos like these only appear on my tl when I am feeling lonely single feel krne lag Jata ? https://t.co/Iz5e8LjEoq",
						"Its_Aliain"),
				new Tweet("@scftsbns I am feeling lonely... Just my \"I think friends\" ignoring me..", "txt_tobinn"),
				new Tweet("I am feeling lonely and sad. This is unpleasant.", "MikeCrew5"),
				new Tweet(
						"i am feeling LONELY and drarry is just really punching me in the face with their relationship",
						"drarrybrainrot"),
				new Tweet("I am feeling lonely rn https://t.co/adnWIe8Nmp", "OshyTheArtist"),
				new Tweet("@SUH0SBABYGIRL I AM FEELING LONELY", "b1gb00tynina"),
				new Tweet(
						"I have nothing to do so I am feeling lonely lol even my bff are busy or lazy we don't text everyday.",
						"HanaViolet_"),
				new Tweet(
						"nice again i am feeling lonely and wondering if i?ll ever share mutual feelings with someone",
						"drippyboyee"),
				new Tweet("someone spam my cc i am feeling lonely and sadhttps://t.co/rjuO9Qw59N", "Nebsxolotl"),
				new Tweet("haha lol poggers here i am feeling lonely bc no one talks to me", "y4gen"),
				new Tweet("I am feeling lonely all of a sudden and I don't know why. :( Send hugs. :<", "dzejaxxix"),
				new Tweet("Wow i am feeling lonely as fuck rn", "szaplz"),
				new Tweet("I am feeling lonely rn", "mamahaarsy"),
				new Tweet("Come back I am feeling lonely", "alexandrinebbb"),
				new Tweet("Idk but i am feeling lonely and depressed", "renaprm"),
				new Tweet("@mostShrifbchaa I am feeling lonely here.", "ShrutikaSD"),
				new Tweet("i'm drunk ask me sometjing i am feeling LONELY", "_apyash"),
				new Tweet("Should be used to be single around the Holidays but yet here I am feeling lonely .",
						"FrankieAraiza4"),
				new Tweet("when I?m alone I am feeling lonely.", "iamnicklovinn"),
				new Tweet("I am feeling lonely..anyway goodnight y'all ??", "koovana"),
				new Tweet(
						"@carpediemeire @TravelBugsWorld @Adventuringgal @LiveaMemory @FitLifeTravel @happytrailshike @tangoandrakija @jenny_travels @180books @Abfabtra vels @perthtravelers @lizzie_hubbard2 @KaliTravel @HopOnMyJourney @ThoroughTripper @Travel_Session @Best_of_Tara @waysyouwander @OfficialBoarder @Roadtrip C @2dancingpandas @EvansKerry @Stromfieldadvs @LisaRivera2207 @gfreetraveler You are not alone John, I am feeling lonely, miserable and stuck too, but we are in this together. There is so much going on this year?",
						"PlanetHopperGal"),
				new Tweet("ahaha just watched cl?s new mv suddenly i am feeling lonely and very single ?", "95zblush"),
				new Tweet(
						"@T_AppleSisLoveU The endless cycle of heat isn't fun, it's so hard to stop sweating when it starts and I'd love to feel cold sometimes you know ? And yeah I am feeling lonely lately, is something the matter?*Blaze hugs Ptilo a bit tighter*",
						"BlazeCarriesU"),
				new Tweet("today i am feeling lonely :]", "hopesheartbeat"),
				new Tweet("does anyone wanna dm or text i am feeling: lonely", "juncheolic"),
				new Tweet("I am feeling lonely ?", "basicvee"),
				new Tweet("Mutuals....Can ya say hello to me?I am feeling lonely", "ThebrotherofYin"),
				new Tweet("My god without base it's too quiet I am feeling lonely", "maximjeo"),
				new Tweet("i want leorio to warm me up during the winteryes i am feeling lonely", "healdiccas"),
				new Tweet(
						"@autumnmckayne Would you like some snail mail? I'm always happy to expand my postcard list. DM me if interested.(I do exactly what you mentione d - reach out to folks when I am feeling lonely.)",
						"SCrow1022"),
				new Tweet("reply with fundy, eret, tubbo and tommy pics please i am feeling lonely?", "Bagelinnit"),
				new Tweet("||Most of my partners are too busy with RL and I am feeling lonely. https://t.co/02Lkb33FJv",
						"ShieldsVixen"),
				new Tweet("#askstudytwt hello please tell me about your day i am feeling lonely lets be besties :(",
						"elliestudies"))));

		// Neutral Tweets for keyword Canada Economy
		mockTweets.put("Canada Economy", new ArrayList<>(Arrays.asList(new Tweet(
				"Canada's provinces financially unstable over long-term, watchdog says. via Reuters https://t.co/7oSnLWupXx #canada #provinces #economy #PBO",
				"a skatrustee"),
				new Tweet(
						"Canada's provinces financially unstable over long-term, watchdog says. via Reuters https://t.co/Vv3qGC1FmC #canada #provinces #economy #PBO",
						"B oaleWood"),
				new Tweet(
						"Visit https://t.co/nFv8Aeep5u for updates on inflation in your province and Canada wide! #MemeFriday #FridayHumour #Inflation #Canada #Economy #CanadianInflation #InflationCalculator https://t.co/iUlSxpFwJc",
						"inflationcanada"),
				new Tweet(
						"Canada's provinces financially unstable over long-term, watchdog says https://t.co/t78zPJXH64 https://t.co/IKGoaW5SrI",
						"fredstg"),
				new Tweet(
						"Canada's provinces financially unstable over long-term, watchdog says https://t.co/dy97U6yHcp",
						"trader363"),
				new Tweet(
						"Canada's provinces financially unstable over long-term, watchdog says https://t.co/qoAiA37RQa https://t.co/NHqLRUngu7",
						"Reuters"),
				new Tweet("??????10??8.36??????????? https://t.co/aeYLUx5E2V https://t.co/VpjfSck7N5",
						"ReutersJapanBiz"),
				new Tweet(
						"Canada job growth slows as shutdowns bite, but analysts see signs of resilience https://t.co/9uVKuKs191",
						"shehzadyounis"),
				new Tweet(
						"Canada job growth slows as shutdowns bite, but analysts see signs of resilience https://t.co/icLJojj58s https://t.co/m8g3besPeF",
						"ReutersBiz"))));

		
				mockTweets.put("Rimal", new ArrayList<>(Arrays.asList(new Tweet(
						"I am happy that audience is appreciating good content on OTT and are looking beyond ?hot? scenes ? @jaysoni25Follow Us on @iwmbuzz @krishnavbha tt@TheRealPriya#JaySoni #KrishnaBhatt #mustread #PriyaBanerjee #Twisted3https://t.co/l6J10HjRJ4",
						"iwmbuzz"),
						new Tweet("@James33172570 Hey sister James, it doesn't matter, I am happy for you got a shiny ??????",
								"shinybaobao"),
						new Tweet(
								"a follow has been sent to you, eonni ? anyway i am a flover and i am happy when you're comeback hehehe https://t.co/fuY30ph0f4",
								"kwcnjian"),
						new Tweet(
								"I am happy!#affirmations #leadership #leadershipdevelopment #management #managementdevelopment #leadershipcoaching #managementcoaching #balance dlife #worklifebalance #lifecoaching https://t.co/6rBtlLa5T4",
								"CoachingforInsp"),
						new Tweet(
								"Thogh I don't watch his channel because of too much level of noise pollution over there yet I am happy to see the SC granting him the bail.#IDo ntSupportRepublic",
								"GaneshJsmith"),
						new Tweet(
								"On this Veterans Day 2020, I am happy to announce the availability of my new book!Conducting performance-based Instructional Analysishttps://t. co/NvgcqUBZbk https://t.co/YDoA4vkNtl",
								"guywwallace"),
						new Tweet(
								"@OfficialMonstaX .My life we ??love you I want to give you happiness every day, you are the most beautiful person in my heart and I am happy wh en you are happy #LoveKilla2ndWin #MONSTAX@OfficialMonstaX",
								"Andyymonbebe"),
						new Tweet(
								"@nessahatake21 I AM SO GLAD YOU LIKE HIM SJDJSJ I am happy when other people love Renji too!! ????????? I?ve been a Renji fan (and simp) for ye ars already ????",
								"AliceLaiho"),
						new Tweet(
								"The only person I compete against is my past self. Have I managed better than yesterday. If the answer is yes I am happy.",
								"RekaSundaram"),
						new Tweet(
								"Democrats couldn't figure out how on earth they could unseat Donald Trump, so they settle for British product (Rigging) sold to Nigeria. But wi th this one rubbish act their journey to White House has been made more difficult. Sorry!!I am happy for their are still good people.",
								"theophiletra"),
						new Tweet("@yumekosh ok am i supposed to be hurt or sum,,, i am happy for you??? ?", "cherrykawa"),
						new Tweet("@DoMe3908 I am happy you feel that way???", "jousuke_SandW"),
						new Tweet(
								"I am happy. You can?t spoil my mood. I am happy. I?m about be fed as a stunt brand shipper that I am. I am happy. You can?t spoil my mood. http s://t.co/pKcdt3wKgu",
								"Nezr1"),
						new Tweet(
								"@SilenceInPolish Wspania?y Genera?, strateg, Cz?owiek.I am happy to learn that another monument will be erected in March in Breda. Wonderful D utchmen !",
								"halina_moulin"),
						new Tweet(
								"@Domidest @norwegian76 @seanhannity I never thought I would say this, but right now I am happy that I live in South Africa and not the US!",
								"Wi ldFigg"),
						new Tweet("I am blessedI cannot be disadvantagedI am happyI am lovedI am thankfulI am grateful",
								"Oluwatosinoj"),
						new Tweet("@weekend3warrior Wow. Not sure if I am happy or fearful to see that?", "mja2231"),
						new Tweet(
								"@DietHeartNews @LDLSkeptic McDonal's is a human cell degrading machine. Manipulated edible solutions to excite your taste buds and reward brai n cells so that you don't only eat one nugget, you eat the whole box.Haven't stepped in one in years. I am happy to eat natural and clean.",
								"isaacsakko"),
						new Tweet(
								"Can we have a group hug warriors..I am happy We are with u sirLet us fight together for SSR to get justice #ArnabIsBack",
								"HopeofSSR"),
						new Tweet("I am Happy?No Words?#ArnabGoswami #arnabisback https://t.co/kOzhllAqbg", "SriramKannan77"),
						new Tweet(
								"@UnSubtleDesi I am happy for Arnab today but my heart is still bleeding for state sponsored terrorism on #sameetthakkar#Anuragpoddar#PalgharSad huLynching",
								"BawaliBaba2"),
						new Tweet("@amu_osu I am happy", "osuAtipir"),
						new Tweet(
								"@dwnews That is good news. On a side note, some friends ask me if I'm running @dwnews Twitter handle for there are occasional/frequent typos in their tweets. I am happy to share the blame ;-)",
								"NafeesRehmanDr"),
						new Tweet(
								"I got to finish this for today's ObeyMember! I am happy that Beel's turn is on 11.11 which is Pocky Day in Japan. ? It's Emmsy (Obey Me OC) and Beelzebub?#OBEYMEmber #obeymebeelzebub #obeyme https://t.co/hDxThCHwkB",
								"089Kiiru"),
						new Tweet(
								"@vinxi67 @AlexTaylorNews I am happy as long as I live in a democracy . Why are you still here?",
								"Globan999"),
						new Tweet(
								"@republic #ArnabisbackI am happy that my brother Arnab Goswami is back https://t.co/kQHDZalxAz",
								"BinduTi26049337"),
						new Tweet("@I__am_happy_ ????", "shiina_OBR"),
						new Tweet("@I__am_happy_ ????????????????????????????????????", "mi_dd_le"),
						new Tweet("@safanator @InternetFF I am happy that there's a sun shining in your mind ??", "WafHeikal"),
						new Tweet(
								"@Dilaikfandom1 @OrmaxMedia @ColorsTV I am happy Viewers Choice Rubina #AbhinavShukla youtube channel #BodhiArts #AbhinavShukla #RubinaDilaik #J asminBhasin #NishantSinghMalkani #SaraGurpal #ShezadDeol #OrmaxCIL #OrmaxMedia #BBTrendMasterRubinaDilaik #BBTrendMasterAbhinavShukla",
								"NayanaSpatil6"),
						new Tweet("good morning, I just overslept for 2 hours and i am happy about it", "_thesunfl4wer_"),
						new Tweet(
								"Good Evening?????I worked late and just got home.?????????????????I am happy to meet my wife and children????????????",
								"dragon_sanfran"),
						new Tweet("I am happy for Ckay", "Eddynonsoo"),
						new Tweet("Yes! I am happy to hear this! https://t.co/Y8bHwPWpgH", "Sruss745"),
						new Tweet(
								"One day - all will be revealed. I am happy to wait. If corruption is proved - it just means this gang of cricks will have their prison sentence delayed. https://t.co/z7UAtiMnRi",
								"ArakapasHash"),
						new Tweet(
								"I am happy with the Justice pronounced by SC. Celebrations for @republic as it is family to us now. We continue our fight for SACH. @TheKavitaS ingh @pradip103 @anujakapurindia @KanganaTeam ?????? ??? ????? ?? ????, ????? ???? ??????? ?????? ????? ?? ???? ??? ?? ?????",
								"AbhijeetShwas"),
						new Tweet("I have acquired diona and childe,,, I am happy", "BunnPrince"),
						new Tweet(
								"@LastQuake @Erthquake @QuakesToday Of course I am happy if I will share with you and everyone interested in it.",
								"GjuziOlgert"),
						new Tweet("@Chums_oliveth I am happy about this ?", "Bryantt____"),
						new Tweet(
								"@LyndseyNoel5 @ReamsElemAP @jodi_seitz @BurwellBernard I am happy to support all of the hard-working, student-centered teachers at Reams! Such a great school!!??",
								"CCPSEducator"),
						new Tweet(
								"@TheDemCoalition I believe in Democracy, the value of the law, and science, which is why I am happy that I voted for #PresidentBidenVPHarris202 0 #BidenWonDay",
								"KathleenLibby8"),
						new Tweet(
								"@pradip103 I am happy to that #ArnabGoswami has released... Thank you Honourable Supreme Court #SupremeCourtofIndia, Thank you @Swamy39",
								"kanha 0909"),
						new Tweet(
								"I am happy to see my family enjoying this night ?While I am here solo kumaen binabagyo pa tas nakiki away pa sa mga pets ko hahahahahahaAng hir ap lang. Sorry if I seek hugs, circles, care, & attention. Mapapa sana all na lang ako. *cry ko na lang deep explanations ko* https://t.co/Ig0Gy0tofn",
								"As heletAquino"),
						new Tweet(
								"I am happy to see that my recent @OrientXXI article has gained some attention from local media. Here it is featured in a Hargeysa-based media o utlet: https://t.co/HQED3YYqBE",
								"IvanUlisesKK"),
						new Tweet(
								"The time has come. I am happy to announce the launch of my website.Please visit https://t.co/EtQHEwr2c9 untuk mengetahui informasi eksklusif da n semua hal tentang kehidupan saya di luar lapangan sepak bola.Terima kasih. ??#MKTheLabel https://t.co/F2hpOgTrsc",
								"marcklok10"),
						new Tweet("@PMewwww I am happy for you!!! Yay!", "msgkttts"),
						new Tweet("I am happy, I am healthy, I am grateful ??", "TimotheaParris"),
						new Tweet(
								"@ishkarnBHANDARI Sir we can see ur happiness also our thats why u write smelling \"baik\" i also wrote \"arnabgoswia\"but i deleted that sir et ni khusi main kya likhungi......sir its not comment to u sir its real win of our legend arnab sir...i am happy also cry....god bless all...thanks sir...",
								"SaritaB18116169"),
						new Tweet("I am happy she lived to see the day #KamalaHarrisVP https://t.co/jxYCyo64fR",
								"scarsdaletalent"),
						new Tweet(
								"@republic I am 76 years old. Last 8 days it has been rest less days.Today I am happy about Arnab bail. Today is Diwali for me. Continue your Fe arless journalism. Long live Arnab",
								"Ragram611"),
						new Tweet(
								"In the last seven days, I've met three people diagnosed with MS. And I am confused. ?I am happy because they contacted and asked me about the l ocal MS community but I'm not sure should I be happy, because the number of people with MS increased in the last couple of years. ????",
								"PPuljic92"),
						new Tweet(
								"@BBKittyF2 Wow, that is a long MRI, I am happy that you were able to get this done. Good luck Kitty.",
								"Jeffloves1"),
						new Tweet(
								"@therealsteavis @GavinBrannan @lukebrodin1 @MLedermueller @johnnyddavidson @TOwolfpack You are indeed correct. I am happy to stand in the prese nce of facts and be corrected ?",
								"CJCShadowsan"),
						new Tweet(
								"@narendramodi thanks to you sir take initiative of Arnav Gwoswami I am happy to he is release now And congratulations victory of biharPappu fail ki tarah Lalu ki rail ( beta) bhi aapne fail kar di Great sir",
								"JITENDR57412204"),
						new Tweet(
								"I am happy becouse my bro happy Aapka ye happy face dekhkar bahut achcha lga love u god bless u @pradip103 @Chandan81849631 https://t.co/QSAt1 G0WCf",
								"preetish1407"),
						new Tweet("wala na kaming classes, i am happy. please stay safe filo oomfs.", "trzaces"),
						new Tweet(
								"I am happy #NitishKumar won #BiharElectionResults2020 But with #BiharResults I missed mujra of @sardesairajdeep in newsroom.As per so called \" Sources\" this time Rajdeep got disco lights too. ????????.",
								"hvalecha"),
						new Tweet("OMGG THEY DESERVE THIS BIG DORM I SWEARRRRR AARGHHH I AM HAPPY", "theonlyheeseung"),
						new Tweet("@JoelOsteen Amen and I am happy", "Donnedada2"),
						new Tweet("@pratibhabarfa @aajtak Yeah I am happy and you", "Saurabh50004958"),
						new Tweet(
								"MICHAEL: I am happy and then, you are very different tastes and you ain't gonna be called, but... my point is... my point of view...98 others.",
								"TheMarkovOffice"),
						new Tweet(
								"@NForeverozge @ozgecangurel Thanks Nina ... Just because you congratulated me, I won my day I am happy to receive the affection of my friends?? ???",
								"rtemis36112006"),
						new Tweet(
								"@ComesAnnemarie Anniemarie, thank you for following me on Twitter. I am happy to follow you back on Twitter.",
								"BrittenumFaye"),
						new Tweet(
								"Book Blitz Time ~ Today I am happy to be part of the Blitz for Winston Wiggles by Megan Cline~ Come read an excerpt + more! https://t.co/pJ1rCR gxy4 #bookbloggers #blogging #wombat #childrensbooks #picturebooks #cute #TheClqRT #TRJForBloggers @lovingblogs @cosyblogclub #BBlogRT https://t.co/sifxvm B68o",
								"Mehsi_Hime"),
						new Tweet(
								"@MweshiSandra @normanchips I am happy you acknowledge the quality of leaders we have in the UPND. We are not short of alternatives unlike the # ChipantepanteClubPF . Who are the leaders in PF? I know you are proud of the minister and so called bulldozer Hon. Lusambo and the other 2 pornstars. So t alented",
								"katezi14"),
						new Tweet(
								"hi! because I am happy to get sungjin update today, I want to do small GA ? ?? Griptok The Demon?? Malaysian Myday ???? 1 winner will be chosen ?? Reply #MAMAVote #DAY6?? Postage all by me ?? End : 12 Nov 2020 0900hrs https://t.co/Ukc7pA2eZf",
								"Douxhyunsik"),
						new Tweet("What if I am happy now ! 5:38 am", "jhonpar39740314"),
						new Tweet(
								"Too much of our energy has been drained, can they even understand this? I am happy that I wouldn?t be having any finals except for my ortho osc e at the end of this semester or else i would be losing my mind ?",
								"asl97nb"),
						new Tweet(
								"I was going to buy this in Japanese one day but now I am happy I can get the English edition instead! https://t.co/OQujZfm3dF",
								"boysloveparty"),
						new Tweet(
								"I am happy today supreme Court of India granted bail to arnab ghoswami. Actually we didn't do anything it's all about pushpendra kulshrestha ha rd work that brings arnab goswami out of jail we always with you sir Jay Shri ram",
								"Kuldeep35704663"),
						new Tweet(
								"@ManojSaru @oppomobileindia Diwali is festival of lights ,gift and happiness. This year is also different from other years.Until I am happy. So I wish to be more happy from OPPO and Manoj Bhai. #OPPODiwaliWithTechGyan",
								"Prince56818029"),
						new Tweet("@ThePushpendra_ Very good I am happy", "MamtoraRamesh"),
						new Tweet(
								"when you are on right path nothing can defeat you, and i am happy that we got justice.? #ArnabGoswami",
								"freedomofthou18"),
						new Tweet(
								"I am happy I am not the only one who thought this was a xxl selfie-stick. #journalistinprogress #microphoneholder ? https://t.co/ULgGKv9jza",
								"D 0verkleeft"),
						new Tweet("@Mark_nificent Bit tired :)But i am happy with my two poems today. Thanks dear MarkLater?",
								"Julita56795382"),
						new Tweet(
								"As for the challenge of gender balance, they debunk a myth of it being justified by age structure. As an enthusiast and having myself benefited from early-career positions, such as those enacted by @regstud journals, I am happy to see at least this 'age balance' good news.",
								"pedrov_amaral"),
						new Tweet("@RadioHolly I am happy about it but also, I don?t know if this will work next week lol",
								"erinava"),
						new Tweet("@michaelfinucane I am happy to be corrected.", "DonaldClarke63"),
						new Tweet(
								"@ArnabGofficial7 Satyamew jayte A. Bhai buhame khushi hai ki aap jail se bahar air and I am to distort to this hospital because that tell you a bout but discord in this hospital but I am happy so I am celebrate to Diwali thanks",
								"RanajeetPatel1"),
						new Tweet(
								"@TheTrueTankMan The only reason I want all under heaven to coagulate is there is no more need to ask which king is ruling here. As long as I do n't see a group of hooligans throat singing while stealing my fancy jar and a suspicious tollgate demanding my last peck of rice, I am happy.",
								"baabaaer"),
						new Tweet(
								"?? I?m glad you are happy to call me friend. I am happy to call you friend. https://t.co/ZGieGyglub",
								"Sayaberri"),
						new Tweet(
								"i just found my oldest felix fan arts from an old 2018(?) folder ahh i am happy i've kinda gone with some progress lately huhuuu",
								"nlixette"),
						new Tweet("Jinki is back all Taemin can think of is SHINee comeback I am happy ?", "Nymley_Quinn"),
						new Tweet(
								"@chilolo27 wow. i am so sorry to hear this my love. this is such a scary experience. i am happy that you are safe and have been treated for you r injuries. rest up ???",
								"duchyofleo"),
						new Tweet(
								"@FamousJerry Good morning, Jerry. I am happy to read it all worked out. We appreciate your kind words and we appreciate your loyalty! Jan",
								"Del ta"),
						new Tweet(
								"I am happy @HardwareUnboxed are making optimised settings videos and pointing out these things too. I really want a cultural shift in the PC s pace to move people away from \"set it to ultra and complain\". It was possible to \"set games to ultra\" at the end of the PS360 era... https://t.co/C0zg DVlj0K",
								"Dachsjaeger"),
						new Tweet(
								"@heatherdawnmay @heatherdawnmay AS LONG AS YOU ANSWER ME WHEN I NEED YOU, I AM HAPPY TO HEAR FROM YOU. YOU ARE A REAL FRIEND AND A BLESSING TO ME. ?? U 2 DEATH!",
								"ElliottLadette"),
						new Tweet(
								"@that_shaman Now i am happy to have paused the game 6 months, i had do this sachiev just with relog my charact (Arenanet really uses the poor m an's method to keep players waiting)",
								"K_Ermite"),
						new Tweet(
								"That said, if you have a strong opinion about what Node.js should do regarding cancellation please _do_ email me (email can be found in the Nod e.js repo readme) or send me a facebook message. I am happy to chat I just don't like the violence of twitter.",
								"benjamingr_"),
						new Tweet(
								"Good morning . Welcome to Mandarin Oriental Hotel. My name is Charles, Is there anything I could help? Hi, may I have a morning call at 6:30, and also prepare a taxi for me at 7:00, I need to go to Toronto Pearson airport . I am happy to help you",
								"JackJackgao13"),
						new Tweet(
								"@FakeMikeGorman Mike, I am happy to check your area status. Please DM your complete home address and include the name of the primary account ho lder. We are here 24/7. Thank you, Mike",
								"CoxHelp"),
						new Tweet(
								"@kyliestan8 Yeah to beat Gaza?s first weeks sales. I am happy with her being number 1 and being the first female to have number 1s in 5 decades .",
								"Loz_wb"),
						new Tweet("I am Happy", "i7lb_l"), new Tweet("@DiamonDie Very cool, I am happy to hear", "m_ashcroft"),
						new Tweet(
								"Once again thanku for this lovely song and beautiful video to giving ous ... Literally _ i have no words to say , how happy I am .. Happy with your all works .. you never disappointed ous ... Thats why you are the Best ... Best wishes for you ??? @ijassmanak https://t.co/OG2YIYXx4j",
								"Inishusingh"),
						new Tweet("@ShefVaidya Don?t give them logic just say I am happy for you.. it for sure hurts more ??",
								"FleckPapa"),
						new Tweet(
								"@Dev136118 Really , now I am happy, Arnab Goswami is Back, #IStandWithArnab ?#Nationuited?#jaidhriRam?#jaihindudtan??",
								"VijayaRana10"),
						new Tweet("#ArnabIsBack I am so excited and I am happy ??????????", "AnjaliBhatkari7"),
						new Tweet(
								"@_nym3r1a @Sys_Revolt @ActivistHat_ @VitalAnon @Technotna @Anonymo13761588 @AnonArgMMM2020 @anonargcordoba @YourAnonClovers @YourMarkLubbers @a nonlk5 @Global_hackers @OpChildSafety1 @2709vhf @Y0urAnonOPS @Terrence_STR @Buckaroo_0_2 @VoxPopuliMx @JuanCalosoooooo @filosofus40 @nonymousglobal_ @Chal ecosAmarill @Madmomanon660 @_d_a_n_i_e_l__b @ItsLulu_7 @LodeVanB @WolfGodOfCorn @Anons_revenge @aMINE96444917 @AmbazoniaTigers @RobertR41182121 @Kaleigh23 673557 @Anonwatch47374 @AnonNewsUS I am happy it does.",
								"LokiUnleashed1"),
						new Tweet("@HMOIndia @AmitShah @crpfindia Thanks for you sir I am happy Arnab is back",
								"RajPalY38560294"))));

				mockTweets.put("Azim", new ArrayList<>(Arrays.asList(new Tweet(
						"hello i am feeling lonely so friend applications are openn or just ask a random question, no pressure to actually form connections hahahttps:// t.co/9sZLT8M4UW",
						"rxnnyroo"),
						new Tweet(
								"Videos like these only appear on my tl when I am feeling lonely single feel krne lag Jata ? https://t.co/Iz5e8LjEoq",
								"Its_Aliain"),
						new Tweet("@scftsbns I am feeling lonely... Just my \"I think friends\" ignoring me..", "txt_tobinn"),
						new Tweet("I am feeling lonely and sad. This is unpleasant.", "MikeCrew5"),
						new Tweet(
								"i am feeling LONELY and drarry is just really punching me in the face with their relationship",
								"drarrybrainrot"),
						new Tweet("I am feeling lonely rn https://t.co/adnWIe8Nmp", "OshyTheArtist"),
						new Tweet("@SUH0SBABYGIRL I AM FEELING LONELY", "b1gb00tynina"),
						new Tweet(
								"I have nothing to do so I am feeling lonely lol even my bff are busy or lazy we don't text everyday.",
								"HanaViolet_"),
						new Tweet(
								"nice again i am feeling lonely and wondering if i?ll ever share mutual feelings with someone",
								"drippyboyee"),
						new Tweet("someone spam my cc i am feeling lonely and sadhttps://t.co/rjuO9Qw59N", "Nebsxolotl"),
						new Tweet("haha lol poggers here i am feeling lonely bc no one talks to me", "y4gen"),
						new Tweet("I am feeling lonely all of a sudden and I don't know why. :( Send hugs. :<", "dzejaxxix"),
						new Tweet("Wow i am feeling lonely as fuck rn", "szaplz"),
						new Tweet("I am feeling lonely rn", "mamahaarsy"),
						new Tweet("Come back I am feeling lonely", "alexandrinebbb"),
						new Tweet("Idk but i am feeling lonely and depressed", "renaprm"),
						new Tweet("@mostShrifbchaa I am feeling lonely here.", "ShrutikaSD"),
						new Tweet("i'm drunk ask me sometjing i am feeling LONELY", "_apyash"),
						new Tweet("Should be used to be single around the Holidays but yet here I am feeling lonely .",
								"FrankieAraiza4"),
						new Tweet("when I?m alone I am feeling lonely.", "iamnicklovinn"),
						new Tweet("I am feeling lonely..anyway goodnight y'all ??", "koovana"),
						new Tweet(
								"@carpediemeire @TravelBugsWorld @Adventuringgal @LiveaMemory @FitLifeTravel @happytrailshike @tangoandrakija @jenny_travels @180books @Abfabtra vels @perthtravelers @lizzie_hubbard2 @KaliTravel @HopOnMyJourney @ThoroughTripper @Travel_Session @Best_of_Tara @waysyouwander @OfficialBoarder @Roadtrip C @2dancingpandas @EvansKerry @Stromfieldadvs @LisaRivera2207 @gfreetraveler You are not alone John, I am feeling lonely, miserable and stuck too, but we are in this together. There is so much going on this year?",
								"PlanetHopperGal"),
						new Tweet("ahaha just watched cl?s new mv suddenly i am feeling lonely and very single ?", "95zblush"),
						new Tweet(
								"@T_AppleSisLoveU The endless cycle of heat isn't fun, it's so hard to stop sweating when it starts and I'd love to feel cold sometimes you know ? And yeah I am feeling lonely lately, is something the matter?*Blaze hugs Ptilo a bit tighter*",
								"BlazeCarriesU"),
						new Tweet("today i am feeling lonely :]", "hopesheartbeat"),
						new Tweet("does anyone wanna dm or text i am feeling: lonely", "juncheolic"),
						new Tweet("I am feeling lonely ?", "basicvee"),
						new Tweet("Mutuals....Can ya say hello to me?I am feeling lonely", "ThebrotherofYin"),
						new Tweet("My god without base it's too quiet I am feeling lonely", "maximjeo"),
						new Tweet("i want leorio to warm me up during the winteryes i am feeling lonely", "healdiccas"),
						new Tweet(
								"@autumnmckayne Would you like some snail mail? I'm always happy to expand my postcard list. DM me if interested.(I do exactly what you mentione d - reach out to folks when I am feeling lonely.)",
								"SCrow1022"),
						new Tweet("reply with fundy, eret, tubbo and tommy pics please i am feeling lonely?", "Bagelinnit"),
						new Tweet("||Most of my partners are too busy with RL and I am feeling lonely. https://t.co/02Lkb33FJv",
								"ShieldsVixen"),
						new Tweet("#askstudytwt hello please tell me about your day i am feeling lonely lets be besties :(",
								"elliestudies"))));

				
				mockTweets.put("Kapil", new ArrayList<>(Arrays.asList(new Tweet(
						"Canada's provinces financially unstable over long-term, watchdog says. via Reuters https://t.co/7oSnLWupXx #canada #provinces #economy #PBO",
						"a skatrustee"),
						new Tweet(
								"Canada's provinces financially unstable over long-term, watchdog says. via Reuters https://t.co/Vv3qGC1FmC #canada #provinces #economy #PBO",
								"B oaleWood"),
						new Tweet(
								"Visit https://t.co/nFv8Aeep5u for updates on inflation in your province and Canada wide! #MemeFriday #FridayHumour #Inflation #Canada #Economy #CanadianInflation #InflationCalculator https://t.co/iUlSxpFwJc",
								"inflationcanada"),
						new Tweet(
								"Canada's provinces financially unstable over long-term, watchdog says https://t.co/t78zPJXH64 https://t.co/IKGoaW5SrI",
								"fredstg"),
						new Tweet(
								"Canada's provinces financially unstable over long-term, watchdog says https://t.co/dy97U6yHcp",
								"trader363"),
						new Tweet(
								"Canada's provinces financially unstable over long-term, watchdog says https://t.co/qoAiA37RQa https://t.co/NHqLRUngu7",
								"Reuters"),
						new Tweet("??????10??8.36??????????? https://t.co/aeYLUx5E2V https://t.co/VpjfSck7N5",
								"ReutersJapanBiz"),
						new Tweet(
								"Canada job growth slows as shutdowns bite, but analysts see signs of resilience https://t.co/9uVKuKs191",
								"shehzadyounis"),
						new Tweet(
								"Canada job growth slows as shutdowns bite, but analysts see signs of resilience https://t.co/icLJojj58s https://t.co/m8g3besPeF",
								"ReutersBiz"))));
		
	}

	/**
	 * Mock method for searching tweets
	 * 
	 * @param keyword Search Keyword
	 * @return CompletionStage containing List of Tweets
	 * @author Azim Surani
	 */
	public CompletionStage<List<Tweet>> searchForKeywordAndGetTweets(final String keyword) {

		return supplyAsync(() -> {
			List<Tweet> tweets = new ArrayList<>();

			mockTweets.get(keyword).stream().forEach(tweet -> tweets.add((Tweet) tweet.clone()));

			if (keyword.equalsIgnoreCase("I am Happy")) {

				mockTweets.get(keyword).addAll(0, tweets.subList(20, 31));

			}

			if (keyword.equalsIgnoreCase("I am feeling Lonely"))

				mockTweets.get(keyword).add(0, tweets.get(11));

			return tweets;

		});

	}
	
	/**
	 * Mock method for searching User
	 * 
	 * @param username Search userTimeline
	 * @return CompletionStage containing List of Tweets
	 * @author Rimal Juneja
	 */

	public CompletionStage<List<Tweet>> getUserTimelineByID(final String username) {

		return supplyAsync(() -> {
			List<Tweet> tweets = new ArrayList<>();

			mockTweets.get(username).stream().forEach(tweet -> tweets.add((Tweet) tweet.clone()));

			if (username.equalsIgnoreCase("Rimal")) {

				mockTweets.get(username).addAll(0, tweets.subList(20, 31));

			}

			if (username.equalsIgnoreCase("Azim"))

				mockTweets.get(username).add(0, tweets.get(11));

			return tweets;
		});

	}

}