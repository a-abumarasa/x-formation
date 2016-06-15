/*
The solution is done using MS-SQL syntax. i.e function ISNULL has different way in Oracle 
*/

--Question 1
select CO.CountryID, CO.name 
from Country as CO join City as CI on CO.CountryID = CI.CountryID
group by CO.CountryID, CO.name
having sum(CI.Population) > 400
 
 
--Question 2 (first way)
Select * from Country
where CountryID not in (
	select CO.CountryID
	from Country as CO join City as CI on CO.CountryID = CI.CountryID join Building as BI on CI.CityID = BI.CityID 
)


--Question 2 (Second way)
select CO.CountryID ,CO.name 
from Country as CO left join City as CI on CO.CountryID = CI.CountryID left join Building as BI on CI.CityID = BI.CityID 
group by CO.CountryID, CO.name 
having sum(ISNULL(BI.BuildingID,0)) =0