using System;
using System.Collections.Generic;
using System.Text;
using TECHNICATEST;

namespace Classlib.Repos
{
    public class PraesidiumRepo
    {
        public TAContext context { get; set; }
        public PraesidiumRepo(TAContext _context)
        {
            context = _context;
        }
    }
}
