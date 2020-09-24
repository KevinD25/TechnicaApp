using System;
using System.Collections.Generic;
using System.Text;
using TECHNICATEST;

namespace Classlib.Repos
{
    public class HomeRepo
    {
        public TAContext context { get; set; }
        public HomeRepo(TAContext _context)
        {
            context = _context;
        }
    }
}
