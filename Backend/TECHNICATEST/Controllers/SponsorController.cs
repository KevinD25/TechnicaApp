using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Classlib;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

// For more information on enabling MVC for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace TECHNICATEST.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class SponsorController : Controller
    {
        public TAContext _ctx { get; set; }

        public SponsorController(TAContext ctx)
        {
            _ctx = ctx;
        }

        [HttpGet]
        public IQueryable<Sponsor> GetSponsors()
        {
            IQueryable<Sponsor> Sponsors = _ctx.Sponsors;
            return Sponsors;
        }



        [Route("{id}")]
        [HttpGet]
        public ActionResult<Sponsor> Get1Sponsor(int ID)
        {
            var Spons = _ctx.Sponsors.Include(S => S.vacatures).SingleOrDefault(I => I.id == ID);
            if (Spons == null)
                return NotFound();
            return Spons;
        }
        [HttpPut]
        [Authorize]

        public ActionResult<Sponsor> EditSponsors([FromBody] Sponsor spons)
        {
            _ctx.Sponsors.Update(spons);
            _ctx.SaveChanges();
            return Created("", spons);
        }



        [HttpPost]
        [Authorize]

        public ActionResult<Sponsor> AddSponsor([FromBody] Sponsor spons)
        {
            _ctx.Sponsors.Add(spons);
            _ctx.SaveChanges();
            return Created("", spons);
        }


        [Route("{id}")]
        [HttpDelete]
        [Authorize]

        public ActionResult<Sponsor> DeleteSponsor0(int ID)
        {
            var Sponsor = _ctx.Sponsors.Find(ID);
            if (Sponsor == null)
                return NotFound();

            _ctx.Sponsors.Remove(Sponsor);
            _ctx.SaveChanges();

            var bijhorendeVac = _ctx.vacatures.Where(A => A.sponsorid == ID);
            foreach(var Vac in bijhorendeVac)
            {
                _ctx.vacatures.Remove(Vac);
            }
            _ctx.SaveChanges();
            return NoContent();
        }
    }
}
