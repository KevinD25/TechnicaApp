using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Classlib;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;

namespace TECHNICATEST.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class VacatureController : Controller
    {
      
        
            public TAContext _ctx { get; set; }

            public VacatureController(TAContext ctx)
            {
                _ctx = ctx;
            }

            [HttpGet]
            public IList<Vacature> Getvacatures()
            {
                IQueryable<Vacature> Vac = _ctx.vacatures;
                return Vac.ToList();
            }

        [Route("{id}")]
        [HttpGet]
        public ActionResult<Vacature> Get1Vacature(int ID)
        {
            var vac = _ctx.vacatures.Include(I => I.sponsor).SingleOrDefault(A=>A.id == ID);
            if (vac == null)
                return NotFound();
            return vac;
        }
        [HttpPut]
        public ActionResult<Vacature> EditVacature([FromBody] Vacature vac)
        {
            _ctx.vacatures.Update(vac);
            _ctx.SaveChanges();
            return Created("", vac);
        }



        [HttpPost]
        public ActionResult<Vacature> AddVacature([FromBody] Vacature vac)
        {
            _ctx.vacatures.Add(vac);
            _ctx.SaveChanges();
            return Created("", vac);
        }


        [Route("{id}")]
        [HttpDelete]
        public ActionResult<Vacature> DeleteVacature(int ID)
        {
            var Vacature = _ctx.vacatures.Find(ID);
            if (Vacature == null)
                return NotFound();

            _ctx.vacatures.Remove(Vacature);
            _ctx.SaveChanges();
            return NoContent();
        }
    }
}