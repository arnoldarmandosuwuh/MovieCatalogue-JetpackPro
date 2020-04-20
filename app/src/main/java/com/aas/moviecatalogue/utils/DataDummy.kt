package com.aas.moviecatalogue.utils

import com.aas.moviecatalogue.R
import com.aas.moviecatalogue.data.MovieEntity
import com.aas.moviecatalogue.data.TvShowEntity

object DataDummy {

    fun generateMovieData(): MutableList<MovieEntity> {
        val data = mutableListOf<MovieEntity>()

        data.clear()

        data.add(
            MovieEntity(
                "A Star is Born",
                "A Star Is Born is a 2018 American musical romantic drama film produced and directed by Bradley Cooper and written by Eric Roth, Cooper and Will Fetters. A remake of the 1937 film of the same name, it stars Cooper, Lady Gaga, Dave Chappelle, Andrew Dice Clay, and Sam Elliott, and follows a hard-drinking musician (Cooper) who discovers and falls in love with a young singer (Gaga). It marks the third remake of the original 1937 film, after the 1954 musical and the 1976 musical.",
                R.drawable.poster_a_start_is_born,
                "October 5, 2018",
                "148 minutes",
                "Warner Bros. Pictures"
            )
        )
        data.add(
            MovieEntity(
                "Alita Battle Angel",
                "Alita: Battle Angel is a 2019 American cyberpunk action film based on the 1990s Japanese manga series Gunnm (known as Battle Angel Alita outside Japan) by Yukito Kishiro. Directed by Robert Rodriguez, the film is co-produced by James Cameron and written by Cameron and Laeta Kalogridis. Rosa Salazar stars as the titular heroine Alita, an amnesiac cyborg girl who sets out to learn about her destiny after she awakens in a new body with no past memory of who she is. Christoph Waltz, Jennifer Connelly, Mahershala Ali, Ed Skrein, Jackie Earle Haley and Keean Johnson also star in supporting roles.",
                R.drawable.poster_alita,
                "February 14, 2019",
                "122 minutes",
                "20th Century Fox"
            )
        )
        data.add(
            MovieEntity(
                "Aquaman",
                "Aquaman is a 2018 American superhero film based on the DC Comics character Aquaman and distributed by Warner Bros. Entertainment. It is the sixth installment in the DC Extended Universe (DCEU). The film is directed by James Wan, from a screenplay by David Leslie Johnson-McGoldrick and Will Beall, and is based on a story written by Geoff Johns, Wan and Beall. Aquaman stars Jason Momoa as the title character, with Amber Heard, Willem Dafoe, Patrick Wilson, Dolph Lundgren, Yahya Abdul-Mateen II, and Nicole Kidman in supporting roles. It is the third live-action theatrical film featuring Aquaman, following Batman v Superman: Dawn of Justice (2016) and Justice League (2017), and the first full-length feature film centered around the character. In the film, the titular character learns he is the heir to the underwater kingdom of Atlantis and must step forward to lead his people against his half-brother, Orm, who seeks to unite the seven underwater kingdoms against the surface world.",
                R.drawable.poster_aquaman,
                "December 21, 2018",
                "143 minutes",
                "Warner Bros. Pictures"
            )
        )
        data.add(
            MovieEntity(
                "Bohemian Rhapsody",
                "Bohemian Rhapsody is a 2018 biographical musical film, named after the song Bohemian Rhapsody, about Freddie Mercury, lead singer of the British rock band Queen. It follows the singers life from the formation of the band up to their 1985 Live Aid performance at the original Wembley Stadium. It was directed by Bryan Singer from a screenplay by Anthony McCarten, and produced by Graham King and Queen manager Jim Beach. It stars Rami Malek as Mercury, with Lucy Boynton, Gwilym Lee, Ben Hardy, Joe Mazzello, Aidan Gillen, Tom Hollander, Allen Leech, and Mike Myers in supporting roles. Queen members Brian May and Roger Taylor served as consultants. A British-American venture, the film was produced by 20th Century Fox, Regency Enterprises, GK Films, and Queen Films, with Fox serving as distributor.",
                R.drawable.poster_bohemian,
                "2 November 2018",
                "134 minutes",
                "20th Century Fox"
            )
        )
        data.add(
            MovieEntity(
                "Cold Pursuit",
                "Cold Pursuit is a 2019 American-British black comedy action film directed by Hans Petter Moland (in his Hollywood debut) from a screenplay by Frank Baldwin. The film stars Liam Neeson, Tom Bateman, Tom Jackson, Emmy Rossum, Domenick Lombardozzi, Julia Jones, John Doman, and Laura Dern. It is an official remake of the 2014 Norwegian film In Order of Disappearance (Kraftidioten), also directed by Moland, and follows a vengeful snowplow driver who starts killing the members of a drug cartel following the murder of his son.",
                R.drawable.poster_cold_persuit,
                "February 8, 2019",
                "118 minutes",
                "Summit Entertainment"
            )
        )
        data.add(
            MovieEntity(
                "Creed II",
                "Creed II is a 2018 American sports drama film directed by Steven Caple Jr., and written by Sylvester Stallone and Juel Taylor from a story by Sascha Penn and Cheo Hodari Coker. A sequel to Creed (2015) and the eighth installment in the Rocky film series, it stars Michael B. Jordan, Stallone, Tessa Thompson, Dolph Lundgren, Florian Munteanu, Wood Harris, and Phylicia Rashad. Creed writer-director Ryan Coogler serves as an executive producer on the film. The film follows a fight over 33 years in the making, as Adonis Creed meets a new adversary in the ring: Viktor Drago, son of Ivan Drago, the powerful athlete who killed Adonis father Apollo Creed.",
                R.drawable.poster_creed,
                "November 21, 2018",
                "130 minutes",
                "Warner Bros. Pictures"
            )
        )
        data.add(
            MovieEntity(
                "Fantastic Beasts: The Crimes of Grindelwald",
                "Fantastic Beasts: The Crimes of Grindelwald is a 2018 fantasy film directed by David Yates and written by J. K. Rowling. A joint British and American production, it is the sequel to Fantastic Beasts and Where to Find Them (2016). It is the second instalment in the Fantastic Beasts film series, and the tenth overall in the Wizarding World franchise, which began with the Harry Potter film series. The film features an ensemble cast that includes Eddie Redmayne, Katherine Waterston, Dan Fogler, Alison Sudol, Ezra Miller, Zoë Kravitz, Callum Turner, Claudia Kim, William Nadylam, Kevin Guthrie, Jude Law, and Johnny Depp. The plot follows Newt Scamander and Albus Dumbledore as they attempt to take down the dark wizard Gellert Grindelwald, while facing new threats in a more divided wizarding world.",
                R.drawable.poster_crimes,
                "16 November 2018",
                "134 minutes",
                "Warner Bros. Pictures"
            )
        )
        data.add(
            MovieEntity(
                "Glass",
                "Glass is a 2019 American psychological superhero thriller film written and directed by M. Night Shyamalan, who also producer with Jason Blum, Marc Bienstock and Ashwin Rajan. The film is a crossover and sequel to Shyamalans previous films Unbreakable (2000) and Split (2016), serving as the final installment in the Unbreakable trilogy.[8] Bruce Willis, Samuel L. Jackson, Spencer Treat Clark, and Charlayne Woodard reprise their Unbreakable roles, while James McAvoy and Anya Taylor-Joy return as their Split characters,[9] with Sarah Paulson, Adam David Thompson, and Luke Kirby joining the cast. In the film, David Dunn becomes locked in a mental hospital alongside his archenemy Mr. Glass, as well as the multi-personality \"The Horde\", and must contend with a psychiatrist who is out to prove the trio do not actually possess super-human abilities.",
                R.drawable.poster_glass,
                "January 18, 2019",
                "129 minutes",
                "Universal Pictures"
            )
        )
        data.add(
            MovieEntity(
                "How To Train Your Dragon",
                "How to Train Your Dragon is a 2010 American computer-animated action fantasy film loosely based on the 2003 book of the same name by British author Cressida Cowell, produced by DreamWorks Animation and distributed by Paramount Pictures. The film was directed by Chris Sanders and Dean DeBlois from a screenplay by Will Davies, Sanders, and DeBlois, and stars the voices of Jay Baruchel, Gerard Butler, Craig Ferguson, America Ferrera, Jonah Hill, Christopher Mintz-Plasse, T. J. Miller, and Kristen Wiig. The story takes place in a mythical Viking world where a young Viking teenager named Hiccup aspires to follow his tribes tradition of becoming a dragon slayer. After finally capturing his first dragon, and with his chance at last of gaining the tribes acceptance, he finds that he no longer wants to kill the dragon and instead befriends it.",
                R.drawable.poster_how_to_train,
                "March 26, 2010",
                "98 minutes",
                "Paramount Pictures"
            )
        )
        data.add(
            MovieEntity(
                "Infinity War",
                "Avengers: Infinity War is a 2018 American superhero film based on the Marvel Comics superhero team the Avengers, produced by Marvel Studios and distributed by Walt Disney Studios Motion Pictures. It is the sequel to 2012s The Avengers and 2015s Avengers: Age of Ultron, and the nineteenth film in the Marvel Cinematic Universe (MCU). It was directed by Anthony and Joe Russo, written by Christopher Markus and Stephen McFeely, and features an ensemble cast including Robert Downey Jr., Chris Hemsworth, Mark Ruffalo, Chris Evans, Scarlett Johansson, Benedict Cumberbatch, Don Cheadle, Tom Holland, Chadwick Boseman, Paul Bettany, Elizabeth Olsen, Anthony Mackie, Sebastian Stan, Danai Gurira, Letitia Wright, Dave Bautista, Zoe Saldana, Josh Brolin, and Chris Pratt. In the film, the Avengers and the Guardians of the Galaxy attempt to prevent Thanos from amassing the all-powerful Infinity Stones in a bid to wipe out half of all life in the universe.",
                R.drawable.poster_infinity_war,
                "April 27, 2018",
                "149 minutes",
                "Walt Disney Studios"
            )
        )

        return data
    }

    fun generateTvShowData(): MutableList<TvShowEntity> {
        val data = mutableListOf<TvShowEntity>()

        data.clear()

        data.add(
            TvShowEntity(
                "Arrow",
                "Arrow is an American superhero television series developed by Greg Berlanti, Marc Guggenheim, and Andrew Kreisberg based on the DC Comics character Green Arrow, a costumed crime-fighter created by Mort Weisinger and George Papp, and is set in the Arrowverse, sharing continuity with other Arrowverse television series. The series premiered in the United States on The CW on October 10, 2012, with international broadcasting taking place in late 2012 and primarily filmed in Vancouver, British Columbia, Canada. Arrow follows billionaire playboy Oliver Queen (Stephen Amell), who claimed to have spent five years shipwrecked on Lian Yu, a mysterious island in the North China Sea, before returning home to Starling City (later renamed \"Star City\") to fight crime and corruption as a secret vigilante whose weapon of choice is a bow and arrow.",
                R.drawable.poster_arrow,
                "October 10, 2012",
                "40–43 minutes",
                "Warner Bros. Television Distribution"
            )
        )
        data.add(
            TvShowEntity(
                "Doom Patrol",
                "Doom Patrol is an American web television series based on the DC Comics superhero team of the same name that premiered on February 15, 2019, on DC Universe. It is a spin-off of Titans, with April Bowlby, Brendan Fraser, and Matt Bomer reprising their roles, as well as Diane Guerrero, Alan Tudyk, Joivan Wade and Timothy Dalton joining the cast. Filming began in Georgia in late August 2018, and the first season consists of 15 episodes.",
                R.drawable.poster_doom_patrol,
                "February 15, 2019",
                "45–60 minutes",
                "Warner Bros. Television Distribution"
            )
        )
        data.add(
            TvShowEntity(
                "Dragon Ball",
                "Dragon Ball (Japanese: ドラゴンボール Hepburn: Doragon Bōru), sometimes styled as Dragonball, is a Japanese media franchise created by Akira Toriyama in 1984. The initial manga, written and illustrated by Toriyama, was serialized in Weekly Shōnen Jump from 1984 to 1995, with the 519 individual chapters collected into 42 tankōbon volumes by its publisher Shueisha. Dragon Ball was initially inspired by the classical Chinese novel Journey to the West, as well as Hong Kong martial arts films. The series follows the adventures of the protagonist, Son Goku, from his childhood through adulthood as he trains in martial arts and explores the world in search of the seven orbs known as the Dragon Balls, which summon a wish-granting dragon when gathered. Along his journey, Goku makes several friends and battles a wide variety of villains, many of whom also seek the Dragon Balls.",
                R.drawable.poster_dragon_ball,
                "1986",
                "30–33 minutes",
                "Japan"
            )
        )
        data.add(
            TvShowEntity(
                "Fairy Tail",
                "Fairy Tail (Japanese: フェアリーテイル Hepburn: Fearī Teiru) is a Japanese manga series written and illustrated by Hiro Mashima. It was serialized in Kodanshas Weekly Shōnen Magazine from August 2, 2006 to July 26, 2017, with the individual chapters collected and published into 63 tankōbon volumes. The story follows the adventures of Natsu Dragneel, a member of the popular wizard guild Fairy Tail, as he searches the fictional world of Earth-land for the dragon Igneel.",
                R.drawable.poster_fairytail,
                "October 12, 2009",
                "30 minutes",
                "Animax Asia"
            )
        )
        data.add(
            TvShowEntity(
                "Family Guy",
                "Family Guy is an American animated sitcom created by Seth MacFarlane for the Fox Broadcasting Company. The series centers on the Griffins, a family consisting of parents Peter and Lois; their children, Meg, Chris, and Stewie; and their anthropomorphic pet dog, Brian. The show is set in the fictional city of Quahog, Rhode Island, and exhibits much of its humor in the form of metafictional cutaway gags that often lampoon American culture.",
                R.drawable.poster_family_guy,
                "January 31, 1999",
                "20–23 minutes",
                "20th Television"
            )
        )
        data.add(
            TvShowEntity(
                "Flash",
                "The Flash is an American superhero television series developed by Greg Berlanti, Andrew Kreisberg, and Geoff Johns, airing on The CW. It is based on the DC Comics character Barry Allen / Flash, a costumed superhero crime-fighter with the power to move at superhuman speeds. It is a spin-off from Arrow, existing in the same fictional universe. The series follows Barry Allen, portrayed by Grant Gustin, a crime scene investigator who gains super-human speed, which he uses to fight criminals, including others who have also gained superhuman abilities.",
                R.drawable.poster_flash,
                "October 7, 2014",
                "42–45 minutes",
                "Warner Bros. Television Distribution"
            )
        )
        data.add(
            TvShowEntity(
                "GOT",
                "Game of Thrones is an American fantasy drama television series created by David Benioff and D. B. Weiss for HBO. It is an adaptation of A Song of Ice and Fire, George R. R. Martins series of fantasy novels, the first of which is A Game of Thrones. The show was both produced and filmed in Belfast and elsewhere in the United Kingdom. Filming locations also included Canada, Croatia, Iceland, Malta, Morocco, and Spain. The series premiered on HBO in the United States on April 17, 2011, and concluded on May 19, 2019, with 73 episodes broadcast over eight seasons.",
                R.drawable.poster_god,
                "April 17, 2011",
                "50–82 minutes",
                "Warner Bros. Television Distribution"
            )
        )
        data.add(
            TvShowEntity(
                "Gotham",
                "Gotham is an American crime drama television series developed by Bruno Heller and based on characters published by DC Comics and appearing in the Batman franchise, primarily those of James Gordon and Bruce Wayne. Danny Cannon directed the pilot, and he is an executive producer along with Heller. The series stars Ben McKenzie as the young James Gordon. It premiered on Fox on September 22, 2014 and concluded on April 25, 2019.",
                R.drawable.poster_gotham,
                "September 22, 2014",
                "42–49 minutes",
                "Warner Bros. Television Distribution"
            )
        )
        data.add(
            TvShowEntity(
                "Grey Anatomy",
                "Greys Anatomy is an American medical drama television series that premiered on March 27, 2005, on the American Broadcasting Company (ABC) as a mid-season replacement. The fictional series focuses on the lives of surgical interns, residents, and attending physicians, as they develop into seasoned doctors while trying to maintain personal lives and relationships. The title is a play on Grays Anatomy, a classic human anatomy textbook first published in 1858 in London and written by Henry Gray. Shonda Rhimes developed the pilot and continues to write for the series; she is also one of the executive producers, along with Betsy Beers, Mark Gordon, Krista Vernoff, Rob Corn, Mark Wilding, and Allan Heinberg. Although the series is set in Seattle (at the fictional Seattle Grace Hospital, later renamed), it is filmed primarily in Los Angeles, California.",
                R.drawable.poster_grey_anatomy,
                "March 27, 2005",
                "43 minutes",
                "Walt Disney Television"
            )
        )
        data.add(
            TvShowEntity(
                "Hanna",
                "Hanna is an American action drama web television series, based on the 2011 film of the same name, on Amazon Video. The series was created and written by David Farr, directed by Sarah Adina Smith, and stars Esme Creed-Miles, Joel Kinnaman, and Mireille Enos. The first episode was made available on Amazon Video as a time-limited preview on February 3, 2019. The full eight-episode first season was released on March 29, 2019. In April 2019, Amazon renewed the series for a second season.",
                R.drawable.poster_hanna,
                "February 3, 2019",
                "47–55 minutes",
                "Prime Video"
            )
        )

        return data
    }
}