using System;
using System.Collections.Generic;
using System.Text;
using TECHNICATEST;

namespace Classlib.Repos
{
    public class SponsorRepo
    {
        public TAContext context { get; set; }
        public SponsorRepo(TAContext _context)
        {
            context = _context;
        }
    }
}
