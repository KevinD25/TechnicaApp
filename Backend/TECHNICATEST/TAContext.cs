using Classlib;
using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace TECHNICATEST
{
    public class TAContext : DbContext
    {
        public TAContext(DbContextOptions<TAContext> options)
                 : base(options)
        {

        }
        public TAContext()
        {

        }

        public DbSet<ClubText> ClubTexts {get;set;}
        
        public DbSet<Home> Home { get; set; }

        public DbSet<Praesidium> Praesidium { get; set; }

        public DbSet<Sponsor> Sponsors { get; set; }
        public DbSet<Vacature> vacatures { get; set; }
        
    }
}
