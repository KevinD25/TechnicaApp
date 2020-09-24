using Classlib.Repos;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;


namespace Classlib.Services
{
    public class ClubTextService
    {
        private ClubTextRepo _Repo { get; set; }
        public ClubTextService(ClubTextRepo Repo)
        {
            _Repo = Repo;
        }

        public IList<ClubText> GetClubText()
        {
            IQueryable<ClubText> TheText = _Repo.GetText();
            return TheText.ToList();
        }
    }
}
