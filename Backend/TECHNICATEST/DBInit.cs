using Classlib;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace TECHNICATEST
{
    public class DBInit
    {
        public static void Initialize(TAContext context)
        {
            context.Database.EnsureCreated();
            if (!context.ClubTexts.Any())
            {
                var ClubText = new ClubText()
                {
                    text = "lorum ipsum"
                };

                context.ClubTexts.Add(ClubText);


                var home = new Home()
                {
                    date = DateTime.Now,
                    imageLink = "HTTPS://WWW.TEST.ORG/"
                };

                var home1 = new Home()
                {
                    date = new DateTime(2020,10,12),
                    imageLink = "HTTPS://WWW.TEST2.ORG/"
                };

                var home2 = new Home()
                {
                    date = new DateTime(2020, 12, 12),
                    imageLink = "yeyeeet://WWW.test.ORG/"
                };
                context.Home.Add(home);

                context.Home.Add(home1);
                context.Home.Add(home2);


                var sponsor1 = new Sponsor()
                {
                    name = "Spons",
                    about = "Lorum ipsum dolor sit ",
                    imageLink = "HTTPS://WWW.test.com",
                    website = "RandomLink"
                };


                context.Sponsors.Add(sponsor1);


                var vacature1 = new Vacature()
                {
                    sponsorid = 1,
                    sponsor = sponsor1
                };

                context.vacatures.Add(vacature1);


                var praesidlid = new Praesidium()
                {
                    studies = "Niks meer",
                    name = "scazi",
                    birthday = "ooit",
                    function = "pro gamer",
                    imageLink = "YEETYEET"
                };
                var zedenmaistah = new Praesidium()
                {
                    name = "Rob amazing Mertens",
                    function = "ZEDENMAAAISSTAAAHH",
                    birthday = "26/02/1998",
                    imageLink = "https://www.testje.com",
                    studies = "EA ICT"
                };

                context.Praesidium.Add(zedenmaistah);
                context.Praesidium.Add(praesidlid);
                
               context.SaveChanges();
            }
        }
    }
}
