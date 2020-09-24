using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using TECHNICATEST;


namespace Classlib.Repos
{
    public class ClubTextRepo
    {
        public TAContext context { get; set; }
        public ClubTextRepo(TAContext _context)
        {
            context = _context;
        }

        public IQueryable<ClubText> GetText()
        {
            IQueryable<ClubText> C = context.ClubTexts;
            return C;
        }
    }
}
