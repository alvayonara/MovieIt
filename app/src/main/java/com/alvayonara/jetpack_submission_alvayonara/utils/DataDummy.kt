package com.alvayonara.jetpack_submission_alvayonara.utils

import com.alvayonara.jetpack_submission_alvayonara.BuildConfig
import com.alvayonara.jetpack_submission_alvayonara.data.MovieEntity
import com.alvayonara.jetpack_submission_alvayonara.data.TvShowEntity

object DataDummy {
    fun generateDummyMovies(): List<MovieEntity> {

        val movies = ArrayList<MovieEntity>()

        movies.add(
            MovieEntity(
                "181812",
                "Star Wars: The Rise of Skywalker",
                "Star Wars: The Rise of Skywalker",
                "en",
                BuildConfig.BASE_URL_POSTER + "o86DbpburjxrqAzEDhXZcyE8pDb.png",
                "The surviving Resistance faces the First Order once again as the journey of Rey, Finn and Poe Dameron continues. With the power and knowledge of generations behind them, the final battle begins.",
                "2019-12-18",
                "142",
                "Released",
                "6.5"
            )
        )
        movies.add(
            MovieEntity(
                "920",
                "Cars",
                "Cars",
                "en",
                BuildConfig.BASE_URL_POSTER + "uq3N2SFj1Y06zA6LzCQPkmBdaaE.jpg",
                "Lightning McQueen, a hotshot rookie race car driven to succeed, discovers that life is about the journey, not the finish line, when he finds himself unexpectedly detoured in the sleepy Route 66 town of Radiator Springs. On route across the country to the big Piston Cup Championship in California to compete against two seasoned pros, McQueen gets to know the town's offbeat characters.",
                "2006-06-08",
                "117",
                "Released",
                "6.7"
            )
        )
        movies.add(
            MovieEntity(
                "359724",
                "Ford v Ferrari",
                "Ford v Ferrari",
                "en",
                BuildConfig.BASE_URL_POSTER + "6ApDtO7xaWAfPqfi2IARXIzj8QS.jpg",
                "American car designer Carroll Shelby and the British-born driver Ken Miles work together to battle corporate interference, the laws of physics, and their own personal demons to build a revolutionary race car for Ford Motor Company and take on the dominating race cars of Enzo Ferrari at the 24 Hours of Le Mans in France in 1966.",
                "2019-11-13",
                "152",
                "Released",
                "7.8"
            )
        )
        movies.add(
            MovieEntity(
                "330457",
                "Frozen II",
                "Frozen II",
                "en",
                BuildConfig.BASE_URL_POSTER + "h6Wi81XNXCjTAcdstiCLRykN3Pa.jpg",
                "Elsa, Anna, Kristoff and Olaf head far into the forest to learn the truth about an ancient mystery of their kingdom.",
                "2019-11-20",
                "104",
                "Released",
                "7.0"
            )
        )
        movies.add(
            MovieEntity(
                "486589",
                "Red Shoes and the Seven Dwarfs",
                "Red Shoes and the Seven Dwarfs",
                "en",
                BuildConfig.BASE_URL_POSTER + "MBiKqTsouYqAACLYNDadsjhhC0.jpg",
                "Princes who have been turned into Dwarfs seek the red shoes of a lady in order to break the spell, although it will not be easy.",
                "2019-07-25",
                "92",
                "Released",
                "6.4"
            )
        )
        movies.add(
            MovieEntity(
                "245891",
                "John Wick",
                "John Wick",
                "en",
                BuildConfig.BASE_URL_POSTER + "5vHssUeVe25bMrof1HyaPyWgaP.jpg",
                "Ex-hitman John Wick comes out of retirement to track down the gangsters that took everything from him.",
                "2014-10-22",
                "101",
                "Released",
                "7.2"
            )
        )
        movies.add(
            MovieEntity(
                "331482",
                "Little Women",
                "Little Women",
                "en",
                BuildConfig.BASE_URL_POSTER + "mSmiB8XjUnR1GSIljuCPGsk0cwX.jpg",
                "Four sisters come of age in America in the aftermath of the Civil War.",
                "2019-12-25",
                "134",
                "Released",
                "8.0"
            )
        )
        movies.add(
            MovieEntity(
                "11",
                "Star Wars",
                "Star Wars",
                "en",
                BuildConfig.BASE_URL_POSTER + "btTdmkgIvOi0FFip1sPuZI2oQG6.jpg",
                "Princess Leia is captured and held hostage by the evil Imperial forces in their effort to take over the galactic Empire. Venturesome Luke Skywalker and dashing captain Han Solo team together with the loveable robot duo R2-D2 and C-3PO to rescue the beautiful princess and restore peace and justice in the Empire.",
                "1977-05-25",
                "121",
                "Released",
                "8.2"
            )
        )
        movies.add(
            MovieEntity(
                "607",
                "Men in Black",
                "Men in Black",
                "en",
                BuildConfig.BASE_URL_POSTER + "t66nzsDMHLzzUe4rImT9qsgFk0B.jpg",
                "After a police chase with an otherworldly being, a New York City cop is recruited as an agent in a top-secret organization established to monitor and police alien activity on Earth: the Men in Black. Agent Kay and new recruit Agent Jay find themselves in the middle of a deadly plot by an intergalactic terrorist who has arrived on Earth to assassinate two ambassadors from opposing galaxies.",
                "1997-07-02",
                "98",
                "Released",
                "7.1"
            )
        )
        movies.add(
            MovieEntity(
                "610892",
                "Violet Evergarden: Eternity and the Auto Memory Doll",
                "ヴァイオレット・エヴァーガーデン 外伝 - 永遠と自動手記人形 -",
                "ja",
                BuildConfig.BASE_URL_POSTER + "gKxbKcJCPp6IH5auhP1tFbMXsJE.jpg",
                "Adaptation of \"Violet Evergarden gaiden\", a spin-off of the novel \"Violet Evergarden\" which was previously adapted as an animated television series by the same team.",
                "2019-09-06",
                "90",
                "Released",
                "8.6"
            )
        )

        return movies
    }

    fun generateDummyTvShows(): List<TvShowEntity> {

        val tvShows = ArrayList<TvShowEntity>()

        tvShows.add(
            TvShowEntity(
                "4614",
                "NCIS",
                "NCIS",
                "en",
                BuildConfig.BASE_URL_POSTER + "fi8EvaWtL5CvoielOjjVvTr7ux3.jpg",
                "From murder and espionage to terrorism and stolen submarines, a team of special agents investigates any crime that has a shred of evidence connected to Navy and Marine Corps personnel, regardless of rank or position.",
                "2003-09-23",
                "45",
                "Returning Series",
                "6.9"
            )
        )
        tvShows.add(
            TvShowEntity(
                "67744",
                "Mindhunter",
                "Mindhunter",
                "en",
                BuildConfig.BASE_URL_POSTER + "fbKE87mojpIETWepSbD5Qt741fp.jpg",
                "An agent in the FBI's Elite Serial Crime Unit develops profiling techniques as he pursues notorious serial killers and rapists.",
                "2017-10-13",
                "50",
                "Returning Series",
                "7.7"
            )
        )
        tvShows.add(
            TvShowEntity(
                "1908",
                "Miami Vice",
                "Miami Vice",
                "en",
                BuildConfig.BASE_URL_POSTER + "6psPEKu03UCiLqBxzeSy7wRQOVN.jpg",
                "The story of the Miami Police Department's vice squad and its efforts to end drug trafficking and prostitution, centered on the unlikely partnership of Sonny Crockett and Ricardo Tubbs - who first meet when Tubbs is undercover in a drug cartel.",
                "1984-09-16",
                "42",
                "Ended",
                "7.3"
            )
        )
        tvShows.add(
            TvShowEntity(
                "60572",
                "Pokémon",
                "ポケモン",
                "ja",
                BuildConfig.BASE_URL_POSTER + "2pcTUs20ysCdA6DZclaPmGXD6ph.jpg",
                "Join Ash Ketchum, accompanied by his partner Pikachu, as he travels through many regions, meets new friends and faces new challenges on his quest to become a Pokémon Master.",
                "1997-04-01",
                "22",
                "Returning Series",
                "6.7"
            )
        )
        tvShows.add(
            TvShowEntity(
                "35610",
                "InuYasha",
                "戦国お伽草子–犬夜叉",
                "ja",
                BuildConfig.BASE_URL_POSTER + "zEHGWEMtqdXXhao8PeRqTmpaE6P.jpg",
                "Kagome Higurashi is a modern day young girl who lives with her family by the old Higure shrine. Unbeknownst to Kagome, she is the reincarnation of priestess Kikyo and posseses the \\\"Jewel of Four Souls\\\" (the Shikon jewel). One ill-fated day, Kagome locates an ancient well near her home and is abruptly transported through the well and into a feudal Japan, inhabited by demons. There, she encounters Inuyasha, son of a powerful demon father and a human mother, who is pinned to a tree by an enchanted arrow.",
                "2000-10-16",
                "24",
                "Ended",
                "7.4"
            )
        )
        tvShows.add(
            TvShowEntity(
                "63926",
                "One-Punch Man",
                "ワンパンマン",
                "ja",
                BuildConfig.BASE_URL_POSTER + "iE3s0lG5QVdEHOEZnoAxjmMtvne.jpg",
                "Saitama is a hero who only became a hero for fun. After three years of “special” training, though, he’s become so strong that he’s practically invincible. In fact, he’s too strong—even his mightiest opponents are taken out with a single punch, and it turns out that being devastatingly powerful is actually kind of a bore. With his passion for being a hero lost along with his hair, yet still faced with new enemies every day, how much longer can he keep it going?",
                "2015-10-04",
                "24",
                "Returning Series",
                "8.0"
            )
        )
        tvShows.add(
            TvShowEntity(
                "75214",
                "Violet Evergarden",
                "ヴァイオレット・エヴァーガーデン",
                "ja",
                BuildConfig.BASE_URL_POSTER + "ImvHbM4GsJJykarnOzhtpG6ax6.jpg",
                "In the aftermath of the continent-spanning, four-years-long war that gripped the industrial fantasy land of Leiden, Violet Evergarden, a 14-year old former child-soldier, is trying to find her place in civilian life as an Auto Memory Doll, a professional ghostwriter of letters for the poor and the rich alike.",
                "2018-01-11",
                "25",
                "Ended",
                "8.0"
            )
        )
        tvShows.add(
            TvShowEntity(
                "30977",
                "A Certain Scientific Railgun",
                "とある科学の超電磁砲",
                "ja",
                BuildConfig.BASE_URL_POSTER + "av91c29m40cQ2YXLIp4V76cktjU.jpg",
                "Misaka’s electro-manipulation abilities – and delightfully destructive Railgun projectile move – make her a rock star in Academy City. The techno-metropolis is packed with supernaturally powered students known as espers, including Misaka’s flirty friend and roommate, Kuroko. She uses her teleportation skills as a member of the Judgment law enforcement team, fighting crime alongside her fellow agent Uiharu. Joined by their friend Saten, a spunky Level 0 esper, Misaka, Kuroko, and Uiharu have a blast taking on danger whenever and wherever it arises. Luckily, in a city full of super-powered gangs, mad scientists, and the occasional weird monster, the girls have no problem finding plenty of excitement, action, and adventure! From the director of Toradora!, take another explosively fun trip into the world of A Certain Magical Index with A Certain Scientific Railgun!",
                "2009-10-02",
                "24",
                "Returning Series",
                "6.7"
            )
        )
        tvShows.add(
            TvShowEntity(
                "30980",
                "A Certain Magical Index",
                "とある魔術の禁書目録",
                "ja",
                BuildConfig.BASE_URL_POSTER + "zNJC5IfgCCP6hXDHgwrULqXWicm.jpg",
                "Kamijo is a student in Academy City, where people use science to develop supernatural abilities. The guy’s got a lot of heart – luckily for a young nun named Index. She’s on the run from a sorcery society that covets the astonishing 103,000 volumes of magical knowledge stored in her memory. When Index stumbles into Kamijo’s life, she find a faithful friend and protector, and while Kamijo’s easily the weakest kid in Academy City, he’s got something else going for him: the Imagine Breaker, an unexplainable power stored in his right hand that negates the powers of others. With scientists and sorcerers attacking from all sides, the Imagine Breaker will definitely come in handy – but it’s Kamijo’s loyalty to Index that will be his greatest weapon in the fight to keep her safe",
                "2008-10-04",
                "25",
                "Ended",
                "7.3"
            )
        )
        tvShows.add(
            TvShowEntity(
                "96402",
                "BOFURI: I Don’t Want to Get Hurt, so I’ll Max Out My Defense.",
                "痛いのは嫌なので防御力に極振りしたいと思います。",
                "ja",
                BuildConfig.BASE_URL_POSTER + "eKr4p1jBL081BwqWJ1zywtJIqMF.jpg",
                "Honjou Kaede is invited by her friend Shiramine Risa to play a virtual reality MMO game with her. While Kaede doesn't dislike games, what she really, truly dislikes is being in pain. She creates a character named Maple, and decides to put all her points in VIT to minimize pain. As a result, she moves slowly, can't use magic, and even a rabbit can get the best of her. But as it turns out, she acquires a skill known as \"Absolute Defense\" as a result of her pumping points into VIT, as well as a \"Counter Skill\" that works against special moves. Now, with her ability to nullify all damage, she goes on adventures.",
                "2020-01-08",
                "24",
                "Returning Series",
                "9.5"
            )
        )

        return tvShows
    }
}