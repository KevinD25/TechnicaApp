using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Classlib;
using Classlib.Services;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;

// For more information on enabling MVC for empty projects, visit https://go.microsoft.com/fwlink/?LinkID=397860

namespace TECHNICATEST.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class PraesidiumController : Controller
    {
        public TAContext _ctx { get; set; }

        public PraesidiumController(TAContext ctx)
        {
            _ctx = ctx;
        }
       

        [HttpGet]
        public IList<Praesidium> GetPraesidium()
        {
            IQueryable<Praesidium> Praes = _ctx.Praesidium.OrderBy(O => O.orderId);
            return Praes.ToList();
        }

        [Route("Praeses")]
        [HttpGet]
        public ActionResult<Praesidium> GetPraeses()
        {
            var praes = _ctx.Praesidium.SingleOrDefault(I => I.orderId == 1);
            if (praes == null)
                return NotFound();
            return praes;
        }

        [Route("{id}")]
        [HttpGet]
        public ActionResult<Praesidium> Get1Praesidium(int ID)
        {
            var praes = _ctx.Praesidium.SingleOrDefault(I => I.id == ID);
            if (praes == null)
                return NotFound();
            return praes;
        }
        [HttpPut]
        [Authorize]

        public ActionResult<Praesidium> EditPraesidium([FromBody] Praesidium praes)
        {
            _ctx.Praesidium.Update(praes);
            _ctx.SaveChanges();
            return Created("", praes);
        }



        [HttpPost]
        [Authorize]

        public ActionResult<Praesidium> AddPraesidium([FromBody] Praesidium praes)
        {
            _ctx.Praesidium.Add(praes);
            _ctx.SaveChanges();
            return Created("", praes);
        }


        [Route("{id}")]
        [HttpDelete]
        [Authorize]

        public ActionResult<Praesidium> DeletePraesidium(int ID)
        {
            var Praesidium = _ctx.Praesidium.Find(ID);
            if (Praesidium == null)
                return NotFound();

            _ctx.Praesidium.Remove(Praesidium);
            _ctx.SaveChanges();
            return NoContent();
        }
    }
}
